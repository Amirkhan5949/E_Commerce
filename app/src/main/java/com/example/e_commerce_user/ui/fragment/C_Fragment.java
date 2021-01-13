package com.example.e_commerce_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.ui.activity.HomeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class C_Fragment extends Fragment {

    private FloatingActionButton fab;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_c_, container, false);
        fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(view1 -> {
            startActivity(new Intent(getContext(), HomeActivity.class));
        });

        return view;
    }
}