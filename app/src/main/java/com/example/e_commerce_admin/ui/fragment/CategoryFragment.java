package com.example.e_commerce_admin.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.GridSpacingItemDecoration;
import com.example.e_commerce_admin.ui.adapter.Cat_Adapter;
import com.example.e_commerce_admin.ui.adapter.Super_cat_Adapter;
import com.example.e_commerce_admin.utils.util;

public class CategoryFragment extends Fragment {
    View view;
    RecyclerView super_cat,catrecycler;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_category, container, false);

        super_cat=view.findViewById(R.id.super_cat);
        catrecycler=view.findViewById(R.id.cat_recycler);

        super_cat.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        super_cat.setAdapter(new Super_cat_Adapter(getContext()));

       catrecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
       catrecycler.addItemDecoration(new GridSpacingItemDecoration(3, util.dpToPx(getContext(),16),true));
        catrecycler.setAdapter(new Cat_Adapter());

        return view;
    }
}