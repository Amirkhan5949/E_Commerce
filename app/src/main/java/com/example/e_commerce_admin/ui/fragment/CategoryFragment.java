package com.example.e_commerce_admin.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.GridSpacingItemDecoration;
import com.example.e_commerce_admin.model.SuperCategory;
import com.example.e_commerce_admin.ui.activity.HomeActivity;
import com.example.e_commerce_admin.ui.adapter.Cat_Adapter;
import com.example.e_commerce_admin.ui.adapter.SigningviewAdapter;
import com.example.e_commerce_admin.ui.adapter.Super_Cat_viewAdapter;
import com.example.e_commerce_admin.ui.adapter.Super_cat_Adapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;



    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_category, container, false);
        ((HomeActivity)getActivity()).setCheckedNavigationItem(2);
        viewPager=view.findViewById(R.id.viewPager);
        tabLayout=view.findViewById(R.id.tabLayout);


        FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.SuperCategory.key)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<SuperCategory> superCategories=new ArrayList<>();
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                            SuperCategory value = dataSnapshot.getValue(SuperCategory.class);
                            superCategories.add(value);
                        }

                        viewPager.setAdapter(new Super_Cat_viewAdapter(getChildFragmentManager(),superCategories));
                        tabLayout.setupWithViewPager(viewPager);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        return view;
    }



}