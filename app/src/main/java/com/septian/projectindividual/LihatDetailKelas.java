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

public class LihatDetailKelas extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_id_kls, edit_tgl_mulai, edit_tgl_selesai, edit_ins_kls, edit_mat_kls;
    private String id;
    private Button btn_tambah_pst, btn_batal_pst;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_kelas);

        toolbar = findViewById(R.id.toolbar_display_kls);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);

        edit_id_kls = findViewById(R.id.edit_id_kls);
        edit_tgl_mulai= findViewById(R.id.edit_tgl_mulai);
        edit_tgl_selesai= findViewById(R.id.edit_tgl_selesai);
        edit_ins_kls= findViewById(R.id.edit_ins_kls);
        edit_mat_kls= findViewById(R.id.edit_mat_kls);
        btn_tambah_pst = findViewById(R.id.btn_tambah_kls);
        btn_batal_pst = findViewById(R.id.btn_batal_kls);

        btn_tambah_pst.setOnClickListener(this);
        btn_batal_pst.setOnClickListener(this);

        // menerima data inten dari fragment InstrukturFragment
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.KLS_ID);
        edit_id_kls.setText(id);


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