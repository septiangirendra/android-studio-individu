package com.septian.projectindividual;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;

public class LihatDetailKelas extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_id_kls, edit_tgl_mulai, edit_tgl_selesai;
    public String id,public_nama_ins, public_nama_mat,spinner_value_ins, spinner_value_mat,
            JSON_STRING_INS, JSON_STRING_MAT;
    private Button btn_tambah_pst, btn_batal_pst;
    Toolbar toolbar;
    private Spinner spinner_ins_kls_edit, spinner_mat_kls_edit;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_kelas);

        toolbar = findViewById(R.id.toolbar_display_kls);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edit_id_kls = findViewById(R.id.edit_id_kls_dtl_dtl);
        edit_tgl_mulai= findViewById(R.id.edit_ins_dtl);
        edit_tgl_selesai= findViewById(R.id.edit_materi_dtl);
        spinner_mat_kls_edit = findViewById(R.id.spinner_mat_kls_edit);
        spinner_ins_kls_edit = findViewById(R.id.spinner_ins_kls_edit);
        btn_tambah_pst = findViewById(R.id.btn_tambah_kls);
        btn_batal_pst = findViewById(R.id.btn_batal_kls);

        btn_tambah_pst.setOnClickListener(this);
        btn_batal_pst.setOnClickListener(this);

        edit_tgl_mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mulai();
            }
        });

        edit_tgl_selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selesai();
            }
        });

        // menerima data inten dari fragment InstrukturFragment
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.KLS_ID);
        edit_id_kls.setText(id);

        // mengambil data JSON
        getJson();
        getDataInstruktur();
        getDataMateri();


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void selesai() {

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                //txt_date.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()).toString());
                edit_tgl_selesai.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.show();
    }

    private void mulai() {

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                //txt_date.setText("Tanggal dipilih : "+dateFormatter.format(newDate.getTime()).toString());
                edit_tgl_mulai.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.show();

    }

    private void getDataMateri() {

        //bantuan dari class AsyncTask
        class GetJson extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailKelas.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_MAT);

                return result;
            }

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING_MAT = message;
//                Log.d("DATA JSON: ", JSON_STRING_MAT);

                spinnerMateri();
            }
        }
        GetJson getJson = new GetJson();
        getJson.execute();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void spinnerMateri() {

        JSONObject jsonObject = null;
        ArrayList<String> listIdMat = new ArrayList<>();
        ArrayList<String> listNamaMat = new ArrayList<>();

        try {
            jsonObject = new JSONObject(JSON_STRING_MAT);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_MAT);
//            Log.d("DATA JSON: ", JSON_STRING_MAT);
//            Toast.makeText(DetailKelasDetailActivity.this, "DATA JSON Result: " + result, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                String id_mat = object.getString(Konfigurasi.TAG_JSON_ID_MAT);
                String nama_mat = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT);
                listIdMat.add(id_mat);
                listNamaMat.add(nama_mat);
            }
//            Toast.makeText(this, "test: "+listNamaKls.toString(), Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, listNamaMat); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_mat_kls_edit.setAdapter(spinnerArrayAdapter);

            spinner_mat_kls_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinner_value_mat = listIdMat.get(i);
//                    Toast.makeText(DetailKelasDetailActivity.this, "True Value: "+spinner_value_kelas, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spinner_mat_kls_edit.setSelection(listNamaMat.indexOf(public_nama_ins), spinner_ins_kls_edit.isForceDarkAllowed());//set selected value in spinner

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void getDataInstruktur() {

        //bantuan dari class AsyncTask
        class GetJson extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailKelas.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse
                        (Konfigurasi.URL_GET_ALL);

                return result;
            }

            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING_INS = message;
                Log.d("DATA JSON: ", JSON_STRING_INS);

                spinnerInstruktur();
            }
        }
        GetJson getJson = new GetJson();
        getJson.execute();

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void spinnerInstruktur() {

        JSONObject jsonObject = null;
        ArrayList<String> listIdIns = new ArrayList<>();
        ArrayList<String> listNamaIns = new ArrayList<>();

        try {
            jsonObject = new JSONObject(JSON_STRING_INS);
            JSONArray result = jsonObject.getJSONArray
                    (Konfigurasi.TAG_JSON_ARRAY);
            Log.d("DATA JSON: ", JSON_STRING_INS);
//            Toast.makeText(DetailKelasDetailActivity.this, "DATA JSON Result: " + result, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                String id_ins = object.getString(Konfigurasi.TAG_JSON_ID_INS);
                String nama_ins = object.getString(Konfigurasi.TAG_JSON_NAMA_INS);
                listIdIns.add(id_ins);
                listNamaIns.add(nama_ins);
            }
//            Toast.makeText(this, "test: "+listNamaKls.toString(), Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, listNamaIns); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_ins_kls_edit.setAdapter(spinnerArrayAdapter);

            spinner_ins_kls_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinner_value_ins = listIdIns.get(i);
//                    Toast.makeText(DetailKelasDetailActivity.this, "True Value: "+spinner_value_kelas, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }

            });

            spinner_ins_kls_edit.setSelection(listNamaIns.indexOf(public_nama_mat), spinner_ins_kls_edit.isForceDarkAllowed());//set selected value in spinner

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    private void getJson() {
        // Mengambil Data Dari Android ke Server
        // Bantuan Dari kelas Asynctask

        class GetJson extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            // ctrl + o pilih OnPreExcetue

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailKelas.this,
                        "Mengambil Data", "Harap Menunggu",
                        false, false);
            }

            // Saat proses ambil data terjadi

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_KLS, id);
                return result;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                displayDetailDataKelas(s);
            }

        }

        GetJson getJson= new GetJson();
        getJson.execute();
    }

    private void displayDetailDataKelas(String s) {

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_KLS);
            JSONObject object = result.getJSONObject(0);

            String tgl_mulai = object.getString(Konfigurasi.TAG_JSON_TGL_MULAI);
            String tgl_selesai = object.getString(Konfigurasi.TAG_JSON_TGL_AKHIR);
            String nama_ins= object.getString(Konfigurasi.TAG_JSON_NAMA_INS_KLS);
            String materi = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT_KLS);

            edit_tgl_mulai.setText(tgl_mulai);
            edit_tgl_selesai.setText(tgl_selesai);
            public_nama_mat = nama_ins;
            public_nama_ins = materi;

        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btn_tambah_pst){
            confirmTambahKelas();
        }else if (v == btn_batal_pst){
            confirmDeleteKelas();
        }
            
    }

    private void confirmTambahKelas() {

        // variable data pegawai yang akan diubah
        final String tgl_mulai = edit_tgl_mulai.getText().toString().trim();
        final String tgl_akhir = edit_tgl_selesai.getText().toString().trim();
        final String id_ins_kls = spinner_value_ins;
        final String id_mat_kls = spinner_value_mat;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to update this data? " +
                "\n Mulai: " + tgl_mulai +
                "\n Selesai: " + tgl_akhir +
                "\n ID Ins: " + id_ins_kls+
                "\n ID Mat: " + id_mat_kls);
        alertDialogBuilder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updatePeserta();

            }
        });

        alertDialogBuilder.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Tidak Ngapa2in
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void updatePeserta() {
        // variable data pegawai yang akan diubah
        final String tgl_mulai = edit_tgl_mulai.getText().toString().trim();
        final String tgl_akhir = edit_tgl_selesai.getText().toString().trim();
        final String id_ins_kls = spinner_value_ins;
        final String id_mat_kls = spinner_value_mat;

        class UpdateDataKelas extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailKelas.this,
                        "Mengubah Data","Harap Tunggu",
                        false,false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_KLS_ID,id);
                params.put(Konfigurasi.KEY_KLS_MULAI,tgl_mulai);
                params.put(Konfigurasi.KEY_KLS_SELESAI,tgl_akhir);
                params.put(Konfigurasi.KEY_ID_INS_KLS,id_ins_kls);
                params.put(Konfigurasi.KEY_ID_MAT_KLS,id_mat_kls);
                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_GET_UPDATE_KLS, params);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();;
                Toast.makeText(LihatDetailKelas.this,
                        "" + s, Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(LihatDetailKelas.this, MainActivity.class);
                myIntent.putExtra("keyName", "kelas");
                startActivity(myIntent);

            }
        }

        UpdateDataKelas updateDataKelas = new UpdateDataKelas();
        updateDataKelas.execute();

    }


    private void confirmDeleteKelas() {

        final String tgl_mulai = edit_tgl_mulai.getText().toString().trim();
        final String tgl_selesai = edit_tgl_selesai.getText().toString().trim();
        final String ins = public_nama_mat;
        final String materi = public_nama_ins;

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to update this data? " +
                "\n Tanggal Mulai : " + tgl_mulai +
                "\n Tanggal Selesai: " + tgl_selesai +
                "\n Instruktur: " + ins +
                "\n Materi: " + materi);
        alertDialogBuilder.setTitle("Delete Data");
        alertDialogBuilder.setIcon(getResources().getDrawable(android.R.drawable.ic_delete));
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteKelas();
                startService(new Intent(LihatDetailKelas.this,
                        InstrukturFragment.class));
            }
        });

        alertDialogBuilder.setNegativeButton("Tidak",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Tidak ngapa-ngapain
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void deleteKelas() {

        class DeleteKelas extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailKelas.this,
                        "Updating...", "Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String s = handler.sendGetRequestParam(Konfigurasi.URL_GET_DELETE_KLS, id);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatDetailKelas.this, ""+s,
                        Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(LihatDetailKelas.this, MainActivity.class);
                myIntent.putExtra("keyName", "kelas");
                startActivity(myIntent);
            }

        }

        DeleteKelas deleteKelas = new DeleteKelas();
        deleteKelas.execute();

    }
}