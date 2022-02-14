package com.septian.projectindividual;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
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

public class PencarianPesertaFragment extends Fragment implements View.OnClickListener {

    private String JSON_STRING, cari;
    private ProgressDialog loading;
    private EditText search_nama_pst;
    private Button button_search;
    ListView listViewPencarian;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pencarian_peserta, container, false);
        search_nama_pst = view.findViewById(R.id.search_nama_mat_srch);
        listViewPencarian = view.findViewById(R.id.listViewPencarianMat);
        button_search = view.findViewById(R.id.button_search_mat);
        button_search.setOnClickListener(this);
        getJsonData();
        return view;

    }

    private void getJsonData() {

        String nama_pst = search_nama_pst.getText().toString().trim();
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
                String result = handler.sendGetResponse(KonfigurasiPencarian.URL_GET_SEARCH_PESERTA,nama_pst);
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
            JSONArray jsonArray = jsonObject.getJSONArray(KonfigurasiPencarian.TAG_JSON_ARRAY_PST_SEARCH);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_pst = object.getString(KonfigurasiPencarian.TAG_JSON_ID_PST_SEARCH);
                String nama_pst = object.getString(KonfigurasiPencarian.TAG_JSON_NAMA_PST_SEARCH);



                HashMap<String, String> peserta = new HashMap<>();
                peserta.put(KonfigurasiPencarian.TAG_JSON_ID_PST_SEARCH, id_pst);
                peserta.put(KonfigurasiPencarian.TAG_JSON_NAMA_PST_SEARCH, nama_pst);

                list.add(peserta);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getContext(), list, R.layout.list_hasil_pencarian_peserta,
                new String[]{"id_pst", "nama_pst"},
                new int[]{R.id.txt_id_ins_srch,R.id.txt_nama_pst_srch}
        );
        listViewPencarian.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        cari = search_nama_pst.getText().toString().trim();
        if (cari.equals("")) {
            alertMessage();
        } else {
            getJsonData();
        }

    }

    private void alertMessage() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Message");
        builder.setMessage("Masukan Data Yang Akan Dicari");
        builder.setIcon(getResources().getDrawable(android.R.drawable.ic_dialog_alert));
        builder.setCancelable(false);
        builder.setNegativeButton("Ok", null);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}