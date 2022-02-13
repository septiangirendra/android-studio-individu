package com.septian.projectindividual;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
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

public class TambahKelas extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_tgl_mulai_kls, edit_tgl_selesai_kls;
    private Button btn_tambah_kls, btn_batal_kls;
    private Spinner spinner_ins_kls, spinner_mat_kls;
    private int spinner_value_mat, spinner_value_ins;
    private String JSON_STRING;
    Toolbar toolbar;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormat;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kelas);


        toolbar = findViewById(R.id.toolbar_kls);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edit_tgl_mulai_kls = findViewById(R.id.edit_tgl_mulai_kls);
        edit_tgl_selesai_kls = findViewById(R.id.edit_tgl_selesai_kls);
        btn_tambah_kls = findViewById(R.id.btn_tambah_kls);
        btn_batal_kls = findViewById(R.id.btn_batal_kls);
        spinner_ins_kls = findViewById(R.id.spinner_nama_kls);
        spinner_mat_kls = findViewById(R.id.spinner_nama_pst);

        dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        // Tanggal Picker
        edit_tgl_mulai_kls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mulai();
            }
        });

        edit_tgl_selesai_kls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selesai();
            }
        });

        // mengambil data untuk ditampilkan dispinner
        getDataIns();
        getDataMat();

        btn_tambah_kls.setOnClickListener(this);
        btn_batal_kls.setOnClickListener(this);
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
                edit_tgl_selesai_kls.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
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
                edit_tgl_mulai_kls.setText(year + "-" + monthOfYear + "-" + dayOfMonth);
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.show();
    }

    private void getDataMat() {

        //bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahKelas.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_MAT);

                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING = message;
                Log.d("DATA JSON: ", JSON_STRING);

                spinnerMat();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();


    }

    private void spinnerMat() {

        JSONObject jsonObject = null;
        ArrayList<String> listId = new ArrayList<>();
        ArrayList<String> listNama = new ArrayList<>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_MAT);
            Log.d("DATA JSON: ", JSON_STRING);
            //Toast.makeText(getActivity(), "DATA JSON" + JSON_STRING, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                String id_mat = object.getString(Konfigurasi.TAG_JSON_ID_MAT);
                String nama_mat = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT);
                listId.add(id_mat);
                listNama.add(nama_mat);
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, listNama); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_mat_kls.setAdapter(spinnerArrayAdapter);

            spinner_mat_kls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinner_value_mat = Integer.parseInt(listId.get(i));
//                    Toast.makeText(TambahKelas.this, "True Value: "+spinner_value, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void getDataIns() {

        //bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahKelas.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL);

                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING = message;
                Log.d("DATA JSON: ", JSON_STRING);

                spinnerIns();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();


    }

    private void spinnerIns() {

        JSONObject jsonObject = null;
        ArrayList<String> listId = new ArrayList<>();
        ArrayList<String> listNama = new ArrayList<>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            Log.d("DATA JSON: ", JSON_STRING);
            //Toast.makeText(getActivity(), "DATA JSON" + JSON_STRING, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                String id_pst = object.getString(Konfigurasi.TAG_JSON_ID_INS);
                String nama_pst = object.getString(Konfigurasi.TAG_JSON_NAMA_INS);
                listId.add(id_pst);
                listNama.add(nama_pst);
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item, listNama); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_ins_kls.setAdapter(spinnerArrayAdapter);

            spinner_ins_kls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinner_value_ins = Integer.parseInt(listId.get(i));
//                    Toast.makeText(TambahKelas.this, "True Value: "+spinner_value, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {

        if (v == btn_tambah_kls) {
            confirmTambahKelas();
        } else if (v == btn_batal_kls) {
            Intent myIntent = new Intent(TambahKelas.this, MainActivity.class);
            myIntent.putExtra("keyName", "kelas");
            startActivity(myIntent);
        }
    }

    private void confirmTambahKelas() {

        //get value text field
        final String tgl_mulai = edit_tgl_mulai_kls.getText().toString().trim();
        final String tgl_selesai = edit_tgl_selesai_kls.getText().toString().trim();

        //Confirmation altert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insert Data");
        builder.setMessage("Are you sure want to insert this data? \n" +
                "\n Tanggal Mulai  : " + tgl_mulai +
                "\n Tanggal Selesai : " + tgl_selesai +
                "\n ID Instruktur : " + spinner_value_ins +
                "\n ID Materi " + spinner_value_mat);
        builder.setIcon(getResources().getDrawable(android.R.drawable.ic_input_add));
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                simpanDataKelas();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private void simpanDataKelas() {

        final String tgl_mulai = edit_tgl_mulai_kls.getText().toString().trim();
        final String tgl_selesai = edit_tgl_selesai_kls.getText().toString().trim();
        final String id_ins_spinner = String.valueOf(spinner_value_ins);
        final String id_mat_spinner = String.valueOf(spinner_value_mat);

        class SimpanDataKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahKelas.this,
                        "Menyimpan Data", "Harap tunggu...",
                        false, false);
            }


            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_KLS_MULAI, tgl_mulai);
                params.put(Konfigurasi.KEY_KLS_SELESAI, tgl_selesai);
                params.put(Konfigurasi.KEY_ID_INS_KLS, id_ins_spinner);
                params.put(Konfigurasi.KEY_ID_MAT_KLS, id_mat_spinner);
                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_GET_ADD_KLS, params);
                //System.out.println("Result" + params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(TambahKelas.this, "pesan: " + message,
                        Toast.LENGTH_SHORT).show();
                clearText();
                Intent myIntent = new Intent(TambahKelas.this, MainActivity.class);
                myIntent.putExtra("keyName", "kelas");
                startActivity(myIntent);
            }
        }
        SimpanDataKelas simpanDataKelas = new SimpanDataKelas();
        simpanDataKelas.execute();
    }

    private void clearText() {

        edit_tgl_mulai_kls.setText("");
        edit_tgl_selesai_kls.setText("");
//        spinner_ins_kls.se("");
        edit_tgl_mulai_kls.requestFocus();

    }
}