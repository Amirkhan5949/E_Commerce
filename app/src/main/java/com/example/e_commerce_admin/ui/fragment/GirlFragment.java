package com.example.e_commerce_admin.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce_admin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GirlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GirlFragment extends Fragment {


    public GirlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_girl, container, false);
    }
}