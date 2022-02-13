package com.septian.projectindividual;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class PencarianMateriFragment extends Fragment implements View.OnClickListener {

    private String JSON_STRING;
    private ProgressDialog loading;
    private EditText search_nama;
    private Button button_search;
    ListView listViewPencarian;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pencarian_materi, container, false);
        search_nama = view.findViewById(R.id.search_nama_mat_srch);
        listViewPencarian = view.findViewById(R.id.listViewPencarianMat);
        button_search = view.findViewById(R.id.button_search_mat);
        button_search.setOnClickListener(this);
        getJsonData();
        return view;
    }

    private void getJsonData() {

        String nama_mat = search_nama.getText().toString().trim();
        class GetJsonData extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Ambil Data",
                        "Mohon Menunggu ...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(KonfigurasiPencarian.URL_GET_SEARCH_MAT, nama_mat);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                Log.d("DATA_JSON :", JSON_STRING);
                displayAllDataInstruktur();
            }

        }

        GetJsonData getJsonData = new GetJsonData();
        getJsonData.execute();

    }

    private void displayAllDataInstruktur() {

        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray jsonArray = jsonObject.getJSONArray(KonfigurasiPencarian.TAG_JSON_ARRAY_MAT_SEARCH);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String materi = object.getString(KonfigurasiPencarian.TAG_JSON_NAMA_MAT_SEARCH);
                String pst = object.getString(KonfigurasiPencarian.TAG_JSON_NAMA_PST_MAT_SEARCH);

                HashMap<String, String> peserta = new HashMap<>();
                peserta.put(KonfigurasiPencarian.TAG_JSON_NAMA_MAT_SEARCH, materi);
                peserta.put(KonfigurasiPencarian.TAG_JSON_NAMA_PST_MAT_SEARCH, pst);

                list.add(peserta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getContext(), list, R.layout.list_hasil_pencarian_mat,
                new String[]{"peserta"},
                new int[]{R.id.txt_nama_pst_srch}
        );
        listViewPencarian.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

        if (v == button_search) {
            getJsonData();
        }
    }
}