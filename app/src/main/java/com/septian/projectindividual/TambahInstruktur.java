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

public class TambahInstruktur extends AppCompatActivity implements View.OnClickListener {

    //Dibawah ini merupakan perintah untuk mendefinikan View
    private EditText tmb_nama_ins, tmb_email_ins, tmb_kontak_ins;
    private Button btn_tambah_ins, btn_batal_ins;
    Toolbar toolbar;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_instruktur);


        toolbar = findViewById(R.id.toolbar_kls);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // inisialisasi variabel atau pengenalan variabel ke layout
        tmb_nama_ins = findViewById(R.id.tmb_nama_pst);
        tmb_email_ins = findViewById(R.id.tmb_email_pst);
        tmb_kontak_ins = findViewById(R.id.tmb_kontak_pst);
        btn_tambah_ins = findViewById(R.id.btn_tambah_kls);
        btn_batal_ins = findViewById(R.id.btn_batal_kls);

        btn_tambah_ins.setOnClickListener(this);
        btn_batal_ins.setOnClickListener(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v == btn_tambah_ins) {
            confirmDataInstruktur();
        } else if (v == btn_batal_ins) {
            Intent myIntent = new Intent(TambahInstruktur.this, MainActivity.class);
            myIntent.putExtra("keyName", "instruktur");
            startActivity(myIntent);
        }
    }

    private void confirmDataInstruktur() {
        final String nama = tmb_nama_ins.getText().toString().trim();
        final String email = tmb_email_ins.getText().toString().trim();
        final String kontak = tmb_kontak_ins.getText().toString().trim();


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambah Data");
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


    // Validasi form
    private boolean CheckAllFields() {

        if (tmb_nama_ins.length() == 0) {
            tmb_nama_ins.setError("This field is required");
            return false;
        }else if (tmb_email_ins.length() == 0) {
            tmb_email_ins.setError("Email is required");
            return false;
        }else if (tmb_kontak_ins.length() == 0) {
            tmb_kontak_ins.setError("This field is required");
            return false;
        }else {
            tambahDataInstruktur();
        }


        // after all validation return true.
        return true;

    }

    private void tambahDataInstruktur() {

        final String nama = tmb_nama_ins.getText().toString().trim();
        final String email = tmb_email_ins.getText().toString().trim();
        final String kontak = tmb_kontak_ins.getText().toString().trim();

        class TambahDataInstruktur extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TambahInstruktur.this,
                        "Menyimpan Data", "Harap Tunggu",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_INS_NAMA, nama);
                params.put(Konfigurasi.KEY_INS_EMAIL, email);
                params.put(Konfigurasi.KEY_INS_KONTAK, kontak);
                HttpHandler handler = new HttpHandler();
                // Kirim data
                String result = handler.sendPostRequest(Konfigurasi.URL_GET_ADD, params);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(TambahInstruktur.this, "" + s,
                        Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(TambahInstruktur.this, MainActivity.class);
                myIntent.putExtra("keyName", "instruktur");
                startActivity(myIntent);
            }
        }

        TambahDataInstruktur tambahDataInstruktur = new TambahDataInstruktur();
        tambahDataInstruktur.execute();
    }
}