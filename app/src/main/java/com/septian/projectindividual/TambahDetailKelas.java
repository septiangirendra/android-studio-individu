package com.septian.projectindividual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TambahDetailKelas extends AppCompatActivity implements View.OnClickListener {

    private Button btn_tambah_kls, btn_batal_kls;
    private Spinner spinner_nama_kls,spinner_nama_pst;
    private int spinner_value, spinner_value_kelas;
    private String JSON_STRING, JSON_STRING_KLS;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_detail_kelas);
        toolbar = findViewById(R.id.toolbar_kls);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        btn_tambah_kls = findViewById(R.id.btn_tambah_kls);
        btn_batal_kls = findViewById(R.id.btn_batal_kls);
        spinner_nama_kls = findViewById(R.id.spinner_nama_kls);
        spinner_nama_pst = findViewById(R.id.spinner_nama_pst);
        
        btn_tambah_kls.setOnClickListener(this);
        btn_batal_kls.setOnClickListener(this);
        
        getDataKelas();
        getDataPeserta();
        
        
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void getDataPeserta() {

        //bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDetailKelas.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_PST);

                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING = message;
                Log.d("DATA JSON: ", JSON_STRING);

                spinnerPeserta();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }

    private void spinnerPeserta() {
        JSONObject jsonObject = null;
        ArrayList<String> listId = new ArrayList<>();
        ArrayList<String> listNama = new ArrayList<>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_PST);
            Log.d("DATA JSON: ", JSON_STRING);
            //Toast.makeText(getActivity(), "DATA JSON" + JSON_STRING, Toast.LENGTH_SHORT).show();

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                String id_pst = object.getString(Konfigurasi.TAG_JSON_ID_PST);
                String nama_pst = object.getString(Konfigurasi.TAG_JSON_NAMA_PST);
                listId.add(id_pst);
                listNama.add(nama_pst);
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item,listNama); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_nama_pst.setAdapter(spinnerArrayAdapter);

            spinner_nama_pst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinner_value = Integer.parseInt(listId.get(i));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    private void getDataKelas() {

        //bantuan dari class AsyncTask
        class GetJSON extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDetailKelas.this,
                        "Mengambil Data", "Harap Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_KLS);

                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                JSON_STRING_KLS = message;
                Log.d("DATA JSON: ", JSON_STRING_KLS);

                spinnerKelas();
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }

    private void spinnerKelas() {

        JSONObject jsonObject = null;
        ArrayList<String> listIdKls = new ArrayList<>();
        ArrayList<String> listNamaKls = new ArrayList<>();

        try {
            jsonObject = new JSONObject(JSON_STRING_KLS);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_KLS);
            Log.d("DATA JSON: ", JSON_STRING_KLS);

            for (int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                String id_kls = object.getString(Konfigurasi.TAG_JSON_ID_KLS);
                String nama_kls = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT_KLS);
                listIdKls.add(id_kls);
                listNamaKls.add(nama_kls);
            }

            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_item,listNamaKls); //selected item will look like a spinner set from XML
            spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner_nama_kls.setAdapter(spinnerArrayAdapter);

            spinner_nama_kls.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    spinner_value_kelas = Integer.parseInt(listIdKls.get(i));
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


        if (v == btn_batal_kls) {
            Intent myIntent = new Intent(TambahDetailKelas.this, MainActivity.class);
            myIntent.putExtra("keyName", "detail_kelas");
            startActivity(myIntent);
        } else if (v == btn_tambah_kls) {
            confirmTambahDetailKelas();
        }

    }

    private void confirmTambahDetailKelas() {

        //get value text field
//        final String kom_id_kls = add_id_kls_dtl_kls.getText().toString().trim();
//        final String kom_id_pst = add_id_pst_dtl_kls.getText().toString().trim();

        //Confirmation altert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Insert Data");
        builder.setMessage("Are you sure want to insert this data? \n" +
                "\n Kelas    : " + spinner_nama_kls.getSelectedItem() +
                "\n Peserta : " + spinner_nama_pst.getSelectedItem());
        builder.setIcon(getResources().getDrawable(android.R.drawable.ic_input_add));
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel",null);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                simpanDataDetailKelas();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();



    }

    private void simpanDataDetailKelas() {

        //        final String id_kls = add_id_kls_dtl_kls.getText().toString().trim();
//        final String id_pst = add_id_pst_dtl_kls.getText().toString().trim();
        final String id_kls_spinner = String.valueOf(spinner_value_kelas);
        final String id_pst_spinner = String.valueOf(spinner_value);

        class SimpanDataDetailKelas extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahDetailKelas.this,
                        "Menyimpan Data", "Harap tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_DTL_KLS_ID_KLS , id_kls_spinner);
                params.put(Konfigurasi.KEY_DTL_KLS_ID_PST, id_pst_spinner);
                HttpHandler handler = new HttpHandler();
                String result = handler.sendPostRequest(Konfigurasi.URL_GET_ADD_DTL_KLS, params);
                //System.out.println("Result" + params);
                return result;
            }

            @Override
            protected void onPostExecute(String message) {
                super.onPostExecute(message);
                loading.dismiss();
                Toast.makeText(TambahDetailKelas.this, "" + message,
                        Toast.LENGTH_SHORT).show();
//                clearText();
                Intent myIntent = new Intent(TambahDetailKelas.this, MainActivity.class);
                myIntent.putExtra("keyName", "detail_kelas");
                startActivity(myIntent);
            }
        }
        SimpanDataDetailKelas simpanDataDetailKelas = new SimpanDataDetailKelas();
        simpanDataDetailKelas.execute();

    }
}