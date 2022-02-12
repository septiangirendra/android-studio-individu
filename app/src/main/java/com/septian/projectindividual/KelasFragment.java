package com.septian.projectindividual;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.septian.projectindividual.databinding.FragmentKelasBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class KelasFragment extends Fragment implements MainActivity.OnBackPressedListener, View.OnClickListener {

    private FragmentKelasBinding kelasBinding;
    private View view;
    private String JSON_STRING;
    private ProgressDialog loading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        kelasBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_kelas,
                container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        view = kelasBinding.getRoot();
        initView();
        return view;

    }

    private void initView() {

        // Custom Action Bar
        ActionBar customActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        customActionBar.setTitle("Data Kelas");

        //penanganan List View
        kelasBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // membuka detail

                Intent myIntent = new Intent(KelasFragment.this.getActivity(), LihatDetailKelas.class);
                HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
                String klsId = map.get(Konfigurasi.TAG_JSON_ID_KLS).toString();
                myIntent.putExtra(Konfigurasi.KLS_ID, klsId);
                startActivity(myIntent);


            }
        });

        // penanganan Floating Action Bar

        kelasBinding.addFabKls.setOnClickListener(this);


        //ambil data dari JSON
        getJsonnData();

    }

    private void getJsonnData() {

        class GetJsonData extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view.getContext(), "Ambil Data ",
                        "Mohon Menunggu ...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_KLS);
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
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_KLS);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_kls = object.getString(Konfigurasi.TAG_JSON_ID_KLS);
                String materi = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT_KLS);
                String nama_ins = object.getString(Konfigurasi.TAG_JSON_NAMA_INS_KLS);

                HashMap<String, String> kelas = new HashMap<>();
                kelas.put(Konfigurasi.TAG_JSON_ID_KLS, id_kls);
                kelas.put(Konfigurasi.TAG_JSON_NAMA_MAT_KLS, materi);
                kelas.put(Konfigurasi.TAG_JSON_NAMA_INS_KLS, nama_ins);

                list.add(kelas);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                view.getContext(), list, R.layout.list_item_layout_kelas,
                new String[]{Konfigurasi.TAG_JSON_ID_KLS, Konfigurasi.TAG_JSON_NAMA_MAT_KLS, Konfigurasi.TAG_JSON_NAMA_INS_KLS},
                new int[]{R.id.txt_kelas, R.id.txt_materi, R.id.txt_ins}
        );

        kelasBinding.listView.setAdapter(adapter);

    }

    @Override
    public void doBack() {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.frameLayout, new HomeFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        // Intent tambah halaman
        startActivity(new Intent(view.getContext(), TambahKelas.class));
    }
}