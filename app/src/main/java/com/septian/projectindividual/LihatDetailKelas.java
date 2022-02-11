package com.septian.projectindividual;

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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class LihatDetailKelas extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_id_kls, edit_tgl_mulai, edit_tgl_selesai, edit_ins_kls, edit_mat_kls;
    private String id;
    private Button btn_tambah_pst, btn_batal_pst;
    Toolbar toolbar;
    private Spinner spinner_ins_kls_edit, spinner_mat_kls_edit;
    String id_kls,public_nama_ins, public_nama_mat,spinner_value_ins, spinner_value_mat,
            JSON_STRING_INS, JSON_STRING_MAT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_kelas);

        toolbar = findViewById(R.id.toolbar_display_kls);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);

        edit_id_kls = findViewById(R.id.edit_id_kls_dtl_dtl);
        edit_tgl_mulai= findViewById(R.id.edit_ins_dtl);
        edit_tgl_selesai= findViewById(R.id.edit_materi_dtl);
        edit_ins_kls = findViewById(R.id.edit_ins_kls);
        spinner_mat_kls_edit = findViewById(R.id.spinner_mat_kls_edit);
        spinner_ins_kls_edit = findViewById(R.id.spinner_ins_kls_edit);
        btn_tambah_pst = findViewById(R.id.btn_tambah_kls);
        btn_batal_pst = findViewById(R.id.btn_batal_kls);

        btn_tambah_pst.setOnClickListener(this);
        btn_batal_pst.setOnClickListener(this);

        // menerima data inten dari fragment InstrukturFragment
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.KLS_ID);
        edit_id_kls.setText(id);

        // mengambil data JSON
//        getDataInstruktur();
//        getDataMateri();
        getJson();

    }

//    private void getDataInstruktur() {
//
//        //bantuan dari class AsyncTask
//        class GetJSON extends AsyncTask<Void, Void, String> {
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(LihatDetailKelas.this,
//                        "Mengambil Data", "Harap Tunggu...",
//                        false, false);
//            }
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                HttpHandler handler = new HttpHandler();
//                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL);
//
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String message) {
//                super.onPostExecute(message);
//                loading.dismiss();
//                JSON_STRING_INS = message;
//                Log.d("DATA JSON: ", JSON_STRING_INS);
//
//                spinnerInstruktur();
//            }
//        }
//        GetJSON getJSON = new GetJSON();
//        getJSON.execute();
//
//    }
//
//    private void spinnerInstruktur() {
//
//        JSONObject jsonObject = null;
//        ArrayList<String> listIdIns = new ArrayList<>();
//        ArrayList<String> listNamaIns = new ArrayList<>();
//
//        try {
//            jsonObject = new JSONObject(JSON_STRING_INS);
//            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
//            Log.d("DATA JSON: ", JSON_STRING_INS);
////            Toast.makeText(DetailKelasDetailActivity.this, "DATA JSON Result: " + result, Toast.LENGTH_SHORT).show();
//
//            for (int i = 0; i < result.length(); i++) {
//                JSONObject object = result.getJSONObject(i);
//                String id_ins = object.getString(Konfigurasi.TAG_JSON_ID_INS);
//                String nama_ins = object.getString(Konfigurasi.TAG_JSON_NAMA_INS);
//                listIdIns.add(id_ins);
//                listNamaIns.add(nama_ins);
//            }
////            Toast.makeText(this, "test: "+listNamaKls.toString(), Toast.LENGTH_SHORT).show();
//
//            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
//                    (this, android.R.layout.simple_spinner_item, listNamaIns); //selected item will look like a spinner set from XML
//            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner_ins_kls_edit.setAdapter(spinnerArrayAdapter);
//
//            spinner_ins_kls_edit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    spinner_value_ins = listIdIns.get(i);
////                    Toast.makeText(DetailKelasDetailActivity.this, "True Value: "+spinner_value_kelas, Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//            });
//
//            spinner_ins_kls_edit.setSelection(listNamaIns.indexOf(public_nama_ins));//set selected value in spinner
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
//

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

        GetJson getJson = new GetJson();
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
            edit_ins_kls.setText(nama_ins);
            edit_mat_kls.setText(materi);
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == btn_tambah_pst){
//            confirmTambahKelas();
        }else if (v == btn_batal_pst){
            confirmDeleteKelas();
        }
            
    }


    private void confirmDeleteKelas() {

        final String tgl_mulai = edit_tgl_mulai.getText().toString().trim();
        final String tgl_selesai = edit_tgl_selesai.getText().toString().trim();
        final String ins = edit_ins_kls.getText().toString().trim();
        final String materi = edit_mat_kls.getText().toString().trim();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to update this data? " +
                "\n Tanggal Mulai : " + tgl_mulai +
                "\n Tanggal Selesai: " + tgl_selesai +
                "\n Instruktur: " + ins +
                "\n Materi: " + materi);

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