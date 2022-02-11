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

public class LihatDetailDataPeserta extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_id_pst, edit_nama_pst, edit_email_pst, edit_kontak_pst;
    private String id;
    private Button btn_update_pst, btn_delete_pst;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_data_peserta);

        toolbar = findViewById(R.id.toolbar_kls);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);

        edit_id_pst = findViewById(R.id.edit_id_kls_dtl_dtl);
        edit_nama_pst = findViewById(R.id.tmb_nama_pst);
        edit_email_pst = findViewById(R.id.edit_email_pst);
        edit_kontak_pst = findViewById(R.id.edit_ins_kls);
        btn_update_pst = findViewById(R.id.btn_tambah_kls);
        btn_delete_pst = findViewById(R.id.btn_batal_kls);

        btn_update_pst.setOnClickListener(this);
        btn_delete_pst.setOnClickListener(this);

        // menerima data inten dari fragment InstrukturFragment
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.PST_ID);
        edit_id_pst.setText(id);


        // mengambil data JSON
        getJson();
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
                loading = ProgressDialog.show(LihatDetailDataPeserta.this,
                        "Mengambil Data", "Harap Menunggu",
                        false, false);
            }

            // Saat proses ambil data terjadi

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_PST, id);
                return result;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                displayDetailDataPeserta(s);
            }

        }

        GetJson getJson = new GetJson();
        getJson.execute();

    }

    @Override
    public void onClick(View v) {
        if (v == btn_update_pst) {
            confirmPeserta();
        } else if (v == btn_delete_pst) {
            confirmPesertaDelete();
        }
    }

    private void confirmPesertaDelete() {
        final String nama_pst = edit_nama_pst.getText().toString().trim();
        final String email_pst = edit_email_pst.getText().toString().trim();
        final String hp_pst = edit_kontak_pst.getText().toString().trim();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to delete this data? " +
                "\n Nama : " + nama_pst +
                "\n Email: " + email_pst +
                "\n No Hp: " + hp_pst);

        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deletePeserta();
                Intent myIntent = new Intent(LihatDetailDataPeserta.this, MainActivity.class);
                myIntent.putExtra("keyName", "peserta");
                startActivity(myIntent);
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

    private void deletePeserta() {

        class DeletePeserta extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDataPeserta.this,
                        "Updating...", "Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String s = handler.sendGetRequestParam(Konfigurasi.URL_GET_DELETE_PST, id);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatDetailDataPeserta.this, "" + s,
                        Toast.LENGTH_SHORT).show();
            }

        }

        DeletePeserta deletePeserta = new DeletePeserta();
        deletePeserta.execute();

    }

    private void confirmPeserta() {

        final String nama_pst = edit_nama_pst.getText().toString().trim();
        final String email_pst = edit_email_pst.getText().toString().trim();
        final String kontak_pst = edit_kontak_pst.getText().toString().trim();


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to update this data? " +
                "\n Nama : " + nama_pst +
                "\n Email: " + email_pst +
                "\n No Hp: " + kontak_pst);
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

        final String nama_pst = edit_nama_pst.getText().toString().trim();
        final String email_pst = edit_email_pst.getText().toString().trim();
        final String hp_pst = edit_kontak_pst.getText().toString().trim();

        class UpdatePeserta extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDataPeserta.this,
                        "Mengambil Data", "Harap Menunggu",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_PST_ID, id);
                hashMap.put(Konfigurasi.KEY_PST_NAMA, nama_pst);
                hashMap.put(Konfigurasi.KEY_PST_EMAIL, email_pst);
                hashMap.put(Konfigurasi.KEY_PST_KONTAK, hp_pst);

                HttpHandler handler = new HttpHandler();
                String s = handler.sendPostRequest(Konfigurasi.URL_GET_UPDATE_PST, hashMap);
                return s;

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                Toast.makeText(LihatDetailDataPeserta.this, "" + s, Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(LihatDetailDataPeserta.this, MainActivity.class);
                myIntent.putExtra("keyName", "peserta");
                startActivity(myIntent);
            }
        }

        UpdatePeserta updatePeserta = new UpdatePeserta();
        updatePeserta.execute();

    }


    private void displayDetailDataPeserta(String s) {

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_PST);
            JSONObject object = result.getJSONObject(0);

            String nama_pst= object.getString(Konfigurasi.TAG_JSON_NAMA_PST);
            String email_pst = object.getString(Konfigurasi.TAG_JSON_EMAIL_PST);
            String hp_pst = object.getString(Konfigurasi.TAG_JSON_HP_PST);

            edit_nama_pst.setText(nama_pst);
            edit_email_pst.setText(email_pst);
            edit_kontak_pst.setText(hp_pst);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}