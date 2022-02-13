package com.septian.projectindividual;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class LihatDetailDataMateri extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_id_mat, edit_nama_mat;
    private String id;
    private Button btn_update_mat, btn_delete_mat;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_data_materi);

        toolbar = findViewById(R.id.toolbar_kls);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edit_id_mat = findViewById(R.id.edit_id_mat);
        edit_nama_mat = findViewById(R.id.edit_nama_mat);
        btn_update_mat = findViewById(R.id.btn_update_mat);
        btn_delete_mat = findViewById(R.id.btn_delete_mat);

        btn_update_mat.setOnClickListener(this);
        btn_delete_mat.setOnClickListener(this);

        // menerima data inten dari fragment InstrukturFragment
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.MAT_ID);
        edit_id_mat.setText(id);


        // mengambil data JSON
        getJson();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
                loading = ProgressDialog.show(LihatDetailDataMateri.this,
                        "Mengambil Data", "Harap Menunggu",
                        false, false);
            }

            // Saat proses ambil data terjadi

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_MAT, id);
                return result;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                displayDetailDataInstruktur(s);
            }

        }

        GetJson getJson = new GetJson();
        getJson.execute();
    }

    private void displayDetailDataInstruktur(String s) {

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_MAT);
            JSONObject object = result.getJSONObject(0);

            String materi = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT);
            edit_nama_mat.setText(materi);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        if (v == btn_update_mat) {
            confirmUpdateMateri();
        } else if (v == btn_delete_mat) {
            confirmDeleteMateri();
        }

    }

    private void confirmUpdateMateri() {
        final String materi = edit_nama_mat.getText().toString().trim();


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to insert this data? " +
                "\n Materi : " + materi);
        alertDialogBuilder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateMater();

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

    private void updateMater() {

        final String materi = edit_nama_mat.getText().toString().trim();
        class UpdateMateri extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDataMateri.this,
                        "Mengambil Data", "Harap Menunggu",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_MAT_ID, id);
                hashMap.put(Konfigurasi.KEY_NAMA_MAT, materi);

                HttpHandler handler = new HttpHandler();
                String s = handler.sendPostRequest(Konfigurasi.URL_GET_UPDATE_MAT, hashMap);
                return s;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                Toast.makeText(LihatDetailDataMateri.this, "" + s, Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(LihatDetailDataMateri.this, MainActivity.class);
                myIntent.putExtra("keyName", "materi");
                startActivity(myIntent);
            }
        }

        UpdateMateri updateMateri = new UpdateMateri();
        updateMateri.execute();

    }

    private void confirmDeleteMateri() {
        final String materi = edit_nama_mat.getText().toString().trim();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Kamu Yakin Ingin Menghapus Materi? \n" + materi);

        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteMateri();
                startService(new Intent(LihatDetailDataMateri.this,
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

    private void deleteMateri() {
        class DeleteMateri extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDataMateri.this,
                        "Updating...", "Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String s = handler.sendGetRequestParam(Konfigurasi.URL_GET_DELETE_MAT, id);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatDetailDataMateri.this, "" + s,
                        Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(LihatDetailDataMateri.this, MainActivity.class);
                myIntent.putExtra("keyName", "materi");
                startActivity(myIntent);
            }

        }

        DeleteMateri deleteMateri = new DeleteMateri();
        deleteMateri.execute();
    }



}