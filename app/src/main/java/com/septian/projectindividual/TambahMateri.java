package com.septian.projectindividual;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class TambahMateri extends AppCompatActivity implements View.OnClickListener {

    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText tmb_nama_mat;
    private Button btn_tambah_mat, btn_batal_mat;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_materi);

        toolbar = findViewById(R.id.toolbar_kls);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        // inisialisasi variabel atau pengenalan variabel ke layout
        tmb_nama_mat = findViewById(R.id.tmb_nama_pst);
        btn_tambah_mat = findViewById(R.id.btn_tambah_kls);
        btn_batal_mat = findViewById(R.id.btn_batal_kls);

        btn_tambah_mat.setOnClickListener(this);
        btn_batal_mat.setOnClickListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == btn_tambah_mat) {
            confirmDataMateri();
        } else if (v == btn_batal_mat) {
            Intent myIntent = new Intent(TambahMateri.this, MainActivity.class);
            myIntent.putExtra("keyName", "materi");
            startActivity(myIntent);
        }
    }

    private void confirmDataMateri() {
            final String materi = tmb_nama_mat.getText().toString().trim();

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Tambah Data");
            builder.setMessage("Are you sure want to insert this data? " +
                    "\n Materi : " + materi );
            builder.setCancelable(false);
            builder.setNegativeButton("BATAL", null);

            builder.setPositiveButton("SUMBIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    tambahMateri();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }

    private void tambahMateri() {

        final String materi = tmb_nama_mat.getText().toString().trim();

        class TambahDataMateri extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahMateri.this,
                        "Menyimpan Data", "Harap Tunggu",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_NAMA_MAT, materi);
                HttpHandler handler = new HttpHandler();
                // Kirim data
                String result = handler.sendPostRequest(Konfigurasi.URL_GET_ADD_MAT, params);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahMateri.this, "pesan :" + s,
                        Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(TambahMateri.this, MainActivity.class);
                myIntent.putExtra("keyName", "materi");
                startActivity(myIntent);
            }
        }

        TambahDataMateri tambahDataMateri = new TambahDataMateri();
        tambahDataMateri.execute();
    }

}