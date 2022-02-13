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

import java.util.HashMap;

public class TambahPeserta extends AppCompatActivity implements View.OnClickListener {

    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText tmb_nama_pst, tmb_email_pst, tmb_kontak_pst;
    private Button btn_tambah_pst, btn_batal_pst;
    Toolbar toolbar;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_peserta);

        toolbar = findViewById(R.id.toolbar_kls);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        // inisialisasi variabel atau pengenalan variabel ke layout
        tmb_nama_pst = findViewById(R.id.tmb_nama_pst);
        tmb_email_pst = findViewById(R.id.tmb_email_pst);
        tmb_kontak_pst = findViewById(R.id.tmb_kontak_pst);
        btn_tambah_pst = findViewById(R.id.btn_tambah_kls);
        btn_batal_pst = findViewById(R.id.btn_batal_kls);

        btn_tambah_pst.setOnClickListener(this);
        btn_batal_pst.setOnClickListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == btn_tambah_pst) {
            confirmDataPeserta();
        } else if (v == btn_batal_pst) {
            Intent myIntent = new Intent(TambahPeserta.this, MainActivity.class);
            myIntent.putExtra("keyName", "peserta");
            startActivity(myIntent);
        }
    }

    private void confirmDataPeserta() {
        final String nama = tmb_nama_pst.getText().toString().trim();
        final String email = tmb_email_pst.getText().toString().trim();
        final String kontak = tmb_kontak_pst.getText().toString().trim();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah Data Pesserta");
        builder.setMessage("Are you sure want to insert this data? " +
                "\n Nama : " + nama +
                "\n Email: " + email +
                "\n No Hp: " + kontak);
        builder.setCancelable(false);
        builder.setNegativeButton("BATAL", null);

        builder.setPositiveButton("SUMBIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                isAllFieldsChecked = CheckAllFields();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    private boolean CheckAllFields() {

        if (tmb_nama_pst.length() == 0) {
            tmb_nama_pst.setError("This field is required");
            return false;
        }else if (tmb_email_pst.length() == 0) {
            tmb_email_pst.setError("Email is required");
            return false;
        }else if (tmb_kontak_pst.length() == 0) {
            tmb_kontak_pst.setError("This field is required");
            return false;
        }else {
            tambahDataPeserta();
        }

        // after all validation return true.
        return true;
    }


    private void tambahDataPeserta() {

        final String nama = tmb_nama_pst.getText().toString().trim();
        final String email = tmb_email_pst.getText().toString().trim();
        final String kontak = tmb_kontak_pst.getText().toString().trim();

        class TambahDataPeserta extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahPeserta.this,
                        "Menyimpan Data", "Harap Tunggu",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_PST_NAMA, nama);
                params.put(Konfigurasi.KEY_PST_EMAIL, email);
                params.put(Konfigurasi.KEY_PST_KONTAK, kontak);
                HttpHandler handler = new HttpHandler();
                // Kirim data
                String result = handler.sendPostRequest(Konfigurasi.URL_GET_ADD_PST, params);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahPeserta.this, "pesan :" + s,
                        Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(TambahPeserta.this, MainActivity.class);
                myIntent.putExtra("keyName", "peserta");
                startActivity(myIntent);
            }
        }

        TambahDataPeserta tambahDataPeserta = new TambahDataPeserta();
        tambahDataPeserta.execute();
    }

}