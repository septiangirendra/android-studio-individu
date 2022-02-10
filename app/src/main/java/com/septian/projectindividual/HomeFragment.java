package com.septian.projectindividual;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.septian.projectindividual.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements MainActivity.OnBackPressedListener {

    private FragmentHomeBinding homeBinding;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // menampilkan custom menu di actionbar
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        ((MainActivity) getActivity()).setOnBackPressedListener(this);
        view = homeBinding.getRoot();
        initView();
        return view;
    }

    private void initView() {
        ActionBar customActionBar = ((MainActivity) getActivity()).getSupportActionBar();
        customActionBar.setTitle("Home");
    }


    @Override
    public void doBack() {

        // menampilkan alert dialog
        new AlertDialog.Builder(getActivity())
                .setTitle("Do you want to exit?")
                .setMessage("Really want to exit?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finishAffinity();
                        getActivity().finish();
                    }
                }).create().show();

    }
}