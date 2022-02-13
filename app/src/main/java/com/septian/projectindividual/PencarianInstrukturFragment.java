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


public class PencarianInstrukturFragment extends Fragment implements View.OnClickListener {

    private String JSON_STRING;
    private ProgressDialog loading;
    private EditText search_nama_ins;
    private Button button_search;
    ListView listViewPencarian;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pencarian_instruktur, container, false);
        search_nama_ins= view.findViewById(R.id.search_nama_ins);
        listViewPencarian = view.findViewById(R.id.listViewPencarianIns);
        button_search = view.findViewById(R.id.button_search_ins_ins);
        button_search.setOnClickListener(this);
        getJsonData();
        return view;
    }

    private void getJsonData() {

        String nama_ins = search_nama_ins.getText().toString().trim();
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
                String result = handler.sendGetResponse(KonfigurasiPencarian.URL_GET_SEARCH_INS,nama_ins);
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
            JSONArray jsonArray = jsonObject.getJSONArray(KonfigurasiPencarian.TAG_JSON_ARRAY_INS_SEARCH);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_ins = object.getString(KonfigurasiPencarian.TAG_JSON_ID_INS_SEARCH);
                String nama_pst = object.getString(KonfigurasiPencarian.TAG_JSON_NAMA_INS_SEARCH);
                String nama_mat = object.getString(KonfigurasiPencarian.TAG_JSON_NAMA_MAT_INS_SEARCH);
                String mulai = object.getString(KonfigurasiPencarian.TAG_JSON_MULAI_KLS_SEARCH);
                String akhir = object.getString(KonfigurasiPencarian.TAG_JSON_AKHIR_KLS_SEARCH);

                HashMap<String, String> ins = new HashMap<>();
                ins.put(KonfigurasiPencarian.TAG_JSON_ID_INS_SEARCH, id_ins);
                ins.put(KonfigurasiPencarian.TAG_JSON_NAMA_INS_SEARCH, nama_pst);
                ins.put(KonfigurasiPencarian.TAG_JSON_NAMA_MAT_INS_SEARCH, nama_mat);
                ins.put(KonfigurasiPencarian.TAG_JSON_MULAI_KLS_SEARCH, mulai);
                ins.put(KonfigurasiPencarian.TAG_JSON_AKHIR_KLS_SEARCH, akhir);

                list.add(ins);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getContext(), list, R.layout.list_hasil_pencarian_ins,
                new String[]{"id_ins", "nama_ins", "nama_mat", "mulai", "akhir"},
                new int[]{R.id.txt_id_ins_srch,R.id.txt_nama_ins_srch, R.id.txt_nama_mat_srch, R.id.mulai, R.id.akhir}
        );
        listViewPencarian.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if (v == button_search){
            getJsonData();
        }

    }
}