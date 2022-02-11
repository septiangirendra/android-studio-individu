package com.septian.projectindividual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.septian.projectindividual.databinding.FragmentDetailKelasBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LihatDetailDetailKelas extends AppCompatActivity {
    private EditText edit_id_kls_dtl, edit_ins_dtl, edit_materi_dtl;
    private String id;
    Toolbar toolbar;
    private String JSON_STRING;
    ListView listView;
    private View view;
    private FragmentDetailKelasBinding detailKelasBinding;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_detail_detail_kelas);


        toolbar = findViewById(R.id.toolbar_display_dtl);
        // khusus toolbar event handling
        setSupportActionBar(toolbar);

        edit_id_kls_dtl = findViewById(R.id.edit_id_kls_dtl_dtl);
        edit_ins_dtl = findViewById(R.id.edit_ins_dtl);
        edit_materi_dtl = findViewById(R.id.edit_materi_dtl);

        // menerima data inten dari fragment InstrukturFragment
        Intent receiveIntent = getIntent();
        id = receiveIntent.getStringExtra(Konfigurasi.KLS_ID);
        edit_id_kls_dtl.setText(id);
        listView = findViewById(R.id.listView);


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
                loading = ProgressDialog.show(LihatDetailDetailKelas.this,
                        "Mengambil Data", "Harap Menunggu",
                        false, false);
            }

            // Saat proses ambil data terjadi

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_DETAIL_DTL_KLS, id);
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
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_DTL_KLS);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_ins = object.getString(Konfigurasi.TAG_JSON_INS_DTL_KLS);
                String nama_mat = object.getString(Konfigurasi.TAG_JSON_MAT_DTL_KLS);
                String id_kls = object.getString(Konfigurasi.TAG_JSON_KLS_DTL_KLS);
                String pst = object.getString(Konfigurasi.TAG_JSON_PST_DTL_KLS);

                edit_id_kls_dtl.setText(id_kls);
                edit_ins_dtl.setText(id_ins);
                edit_materi_dtl.setText(nama_mat);

                HashMap<String, String> materi = new HashMap<>();
                materi.put(Konfigurasi.TAG_JSON_KLS_DTL_KLS, id_kls);
                materi.put(Konfigurasi.TAG_JSON_PST_DTL_KLS, pst);

                Toast.makeText(this, "" + materi, Toast.LENGTH_SHORT).show();
                list.add(materi);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                this, list, R.layout.list_item_layout_detail_kelas,
                new String[]{Konfigurasi.TAG_JSON_KLS_DTL_KLS, Konfigurasi.TAG_JSON_PST_DTL_KLS},
                new int[]{R.id.txt_nama_mat, R.id.txt_id_kls_dtl}
        );

        listView.setAdapter(adapter);

    }
}