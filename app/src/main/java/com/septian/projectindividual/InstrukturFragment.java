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

import com.septian.projectindividual.databinding.FragmentInstrukturBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class InstrukturFragment extends Fragment implements MainActivity.OnBackPressedListener, AdapterView.OnItemClickListener, View.OnClickListener {

    private FragmentInstrukturBinding instrukturBinding;
    private View view;
    private String JSON_STRING;
    private ProgressDialog loading;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        instrukturBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruktur,
               container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        view = instrukturBinding.getRoot();
        initView();

        return view;

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent myIntent = new Intent(InstrukturFragment.this.getActivity(), LihatDetailDataActivityInstruktur.class);
        HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
        String insId = map.get(Konfigurasi.TAG_JSON_ID_INS).toString();
        myIntent.putExtra(Konfigurasi.INS_ID, insId);
        startActivity(myIntent);
    }

    private void initView() {

       // Custom Action Bar
      ActionBar customActionBar = ((MainActivity) getActivity()).getSupportActionBar();
      customActionBar.setTitle("Data Instruktur");

       //penanganan List View
       instrukturBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // membuka detail

                Intent myIntent = new Intent(InstrukturFragment.this.getActivity(),LihatDetailDataActivityInstruktur.class);
                HashMap<String, String> map = (HashMap) parent.getItemAtPosition(position);
                String insId = map.get(Konfigurasi.TAG_JSON_ID_INS).toString();
                myIntent.putExtra(Konfigurasi.INS_ID, insId);
                startActivity(myIntent);


            }
       });

      // penanganan Floating Action Bar

        instrukturBinding.addFab.setOnClickListener(this);



        //ambil data dari JSON
        getJsonnData();
   }

    private void getJsonnData() {
        class GetJsonData extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(view.getContext(), "Ambil Data Instruktur",
                        "Mohon Menunggu ...",
                        false, false);
            }

            @Override
            protected String doInBackground(Void... voids) {
                HttpHandler handler = new HttpHandler();
                String result = handler.sendGetResponse(Konfigurasi.URL_GET_ALL);
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
            JSONArray jsonArray = jsonObject.getJSONArray(Konfigurasi.TAG_JSON_ARRAY);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String id_ins = object.getString(Konfigurasi.TAG_JSON_ID_INS);
                String nama_ins = object.getString(Konfigurasi.TAG_JSON_NAMA_INS);
                String hp_ins = object.getString(Konfigurasi.TAG_JSON_HP_INS);

                HashMap<String, String> instruktur = new HashMap<>();
                instruktur.put(Konfigurasi.TAG_JSON_ID_INS, id_ins);
                instruktur.put(Konfigurasi.TAG_JSON_NAMA_INS, nama_ins);
                instruktur.put(Konfigurasi.TAG_JSON_HP_INS, hp_ins);

                list.add(instruktur);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                view.getContext(), list, R.layout.list_item_layout_instruktur,
                new String[]{Konfigurasi.TAG_JSON_ID_INS, Konfigurasi.TAG_JSON_NAMA_INS, Konfigurasi.TAG_JSON_HP_INS},
                new int[]{R.id.txt_id_mat, R.id.txt_nama_ins, R.id.txt_nohp_ins}
        );

        instrukturBinding.listView.setAdapter(adapter);
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
        startActivity(new Intent(view.getContext(), TambahInstruktur.class));
    }
}