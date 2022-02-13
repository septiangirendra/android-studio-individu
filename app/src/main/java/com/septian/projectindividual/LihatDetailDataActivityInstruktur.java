package com.septian.projectindividual;

import static android.app.PendingIntent.getActivity;

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

public class LihatDetailDataActivityInstruktur extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_id_ins, edit_nama_ins, edit_email_ins, edit_kontak_ins;
    private String id;
    private Button btn_update_ins, btn_delete_ins;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_data_instruktur);

        toolbar = findViewById(R.id.toolbar_kls);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);

        edit_id_ins = findViewById(R.id.edit_id_kls_dtl_dtl);
        edit_nama_ins = findViewById(R.id.tmb_nama_pst);
        edit_email_ins = findViewById(R.id.edit_email_pst);
        edit_kontak_ins = findViewById(R.id.edit_ins_kls);
        btn_update_ins = findViewById(R.id.btn_tambah_kls);
        btn_delete_ins = findViewById(R.id.btn_batal_kls);

        btn_update_ins.setOnClickListener(this);
        btn_delete_ins.setOnClickListener(this);

        // menerima data inten dari fragment InstrukturFragment
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.INS_ID);
        edit_id_ins.setText(id);


        // mengambil data JSON
        getJson();
    }

    private void getJson() {
        // Mengambil Data Dari Android ke Server
        // Bantuan Dari kelas Asynctask

        class GetJson extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            // ctrl + o pilih OnPreExcetue

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDataActivityInstruktur.this,
                        "Mengambil Data", "Harap Menunggu",
                        false, false);
            }

            // Saat proses ambil data terjadi

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL, id);
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

    private void displayDetailDataInstruktur(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);
            JSONObject object = result.getJSONObject(0);

            String nama_ins= object.getString(Konfigurasi.TAG_JSON_NAMA_INS);
            String email_ins = object.getString(Konfigurasi.TAG_JSON_EMAIL_INS);
            String hp_ins = object.getString(Konfigurasi.TAG_JSON_HP_INS);

            edit_nama_ins.setText(nama_ins);
            edit_email_ins.setText(email_ins);
            edit_kontak_ins.setText(hp_ins);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btn_update_ins){
            confirmUpdateInstruktur();
        }else if (v == btn_delete_ins){
            confirmDeleteInstruktur();
        }

    }

    private void confirmUpdateInstruktur() {

        final String nama = edit_nama_ins.getText().toString().trim();
        final String email = edit_email_ins.getText().toString().trim();
        final String kontak = edit_kontak_ins.getText().toString().trim();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to insert this data? " +
                "\n Nama : " + nama +
                "\n Email: " + email +
                "\n No Hp: " + kontak);

        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateInstruktur();

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

    private void updateInstruktur() {
        final String nama = edit_nama_ins.getText().toString().trim();
        final String email = edit_email_ins.getText().toString().trim();
        final String kontak = edit_kontak_ins.getText().toString().trim();

        class UpdateInstruktur extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDataActivityInstruktur.this,
                        "Updating...", "Wait...", false, false);

            }

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Konfigurasi.KEY_INS_ID, id);
                hashMap.put(Konfigurasi.KEY_INS_NAMA, nama);
                hashMap.put(Konfigurasi.KEY_INS_EMAIL, email);
                hashMap.put(Konfigurasi.KEY_INS_KONTAK, kontak);

                HttpHandler handler = new HttpHandler();
                String s = handler.sendPostRequest(Konfigurasi.URL_GET_UPDATE, hashMap);
                return s;
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Intent myIntent = new Intent(LihatDetailDataActivityInstruktur.this, MainActivity.class);
                myIntent.putExtra("keyName", "instruktur");
                startActivity(myIntent);

            }
        }

        UpdateInstruktur ue = new UpdateInstruktur();
        ue.execute();
    }

    private void confirmDeleteInstruktur() {
        final String nama = edit_nama_ins.getText().toString().trim();
        final String email = edit_email_ins.getText().toString().trim();
        final String kontak = edit_kontak_ins.getText().toString().trim();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure want to insert this data? " +
                "\n Nama : " + nama +
                "\n Email: " + email +
                "\n No Hp: " + kontak);

        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteInstruktur();

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

    private void deleteInstruktur() {
        class DeleteInstruktur extends AsyncTask<Void, Void, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LihatDetailDataActivityInstruktur.this,
                        "Updating...", "Tunggu...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String s = handler.sendGetRequestParam(Konfigurasi.URL_GET_DELETE, id);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(LihatDetailDataActivityInstruktur.this, "" + s,
                        Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(LihatDetailDataActivityInstruktur.this, MainActivity.class);
                myIntent.putExtra("keyName", "instruktur");
                startActivity(myIntent);


            }

        }

        DeleteInstruktur deleteInstruktur = new DeleteInstruktur();
        deleteInstruktur.execute();
    }


}