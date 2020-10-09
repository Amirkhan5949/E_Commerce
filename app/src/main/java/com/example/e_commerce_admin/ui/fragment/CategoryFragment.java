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
import com.example.e_commerce_admin.model.SuperCategory;
import com.example.e_commerce_admin.ui.adapter.Cat_Adapter;
import com.example.e_commerce_admin.ui.adapter.Super_cat_Adapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CategoryFragment extends Fragment {
    View view;
    RecyclerView catrecycler;
    private Cat_Adapter adapter;

    final DatabaseReference base = FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Category.key);


    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_category, container, false);

         catrecycler=view.findViewById(R.id.cat_recycler);



       catrecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
       catrecycler.addItemDecoration(new GridSpacingItemDecoration(3, util.dpToPx(getContext(),16),true));

        FirebaseRecyclerOptions<SuperCategory> options =
                new FirebaseRecyclerOptions.Builder<SuperCategory>()
                        .setQuery(base, SuperCategory.class)
                        .build();
        adapter=new Cat_Adapter(options);
        catrecycler.setAdapter(adapter);


        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}