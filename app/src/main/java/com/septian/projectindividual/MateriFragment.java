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

import com.septian.projectindividual.databinding.FragmentMateriBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MateriFragment extends Fragment implements MainActivity.OnBackPressedListener, View.OnClickListener {

    private FragmentMateriBinding materiBinding;
    private View view;
    private String JSON_STRING;
    private ProgressDialog loading;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        materiBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_materi,
                container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        view = materiBinding.getRoot();
        initView();
        return view;
    }

    private void initView() {

        // Custom Action Bar
        ActionBar customActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        customActionBar.setTitle("Data Materi");

        //penanganan List View
        materiBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // membuka detail

                Intent myIntent = new Intent(MateriFragment.this.getActivity(),LihatDetailDataMateri.class);
                HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
                String matId = map.get(Konfigurasi.TAG_JSON_ID_MAT).toString();
                myIntent.putExtra(Konfigurasi.MAT_ID, matId);
                startActivity(myIntent);



            }
        });

        // penanganan Floating Action Bar

           materiBinding.addFabKls.setOnClickListener(this);



        //ambil data dari JSON
        getJsonData();
    }

    private void getJsonData() {

        class GetJsonData extends AsyncTask<Void, Void, String>{

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view.getContext(), "Ambil Data Materi",
                        "Mohon Menunggu ...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL_MAT);
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
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY_MAT);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_mat = object.getString(Konfigurasi.TAG_JSON_ID_MAT);
                String nama_mat = object.getString(Konfigurasi.TAG_JSON_NAMA_MAT);

                HashMap<String, String> materi = new HashMap<>();
                materi.put(Konfigurasi.TAG_JSON_ID_MAT, id_mat);
                materi.put(Konfigurasi.TAG_JSON_NAMA_MAT, nama_mat);

                list.add(materi);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                view.getContext(), list, R.layout.list_item_layout_materi,
                new String[]{Konfigurasi.TAG_JSON_ID_MAT, Konfigurasi.TAG_JSON_NAMA_MAT},
                new int[]{R.id.txt_nama_pst_dtl_dtl, R.id.txt_id_pst_dtl}
        );

        materiBinding.listView.setAdapter(adapter);
    }


    @Override
    public void doBack() {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        transaction.replace(R.id.frameLayout, new MateriFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        // Intent tambah halaman
        startActivity(new Intent(view.getContext(), TambahMateri.class));
    }
}