package com.example.e_commerce_admin.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Category;
import com.example.e_commerce_admin.ui.adapter.CategoryAdapter;
import com.example.e_commerce_admin.ui.adapter.MainSliderAdapter;
import com.example.e_commerce_admin.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class HomeFragment extends Fragment {
     View view;
     private RecyclerView rv_Category,p_recycler;



     public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for getContext() fragment
        view=inflater.inflate(R.layout.fragment_home, container, false);



        Slider slider = view.findViewById(R.id.banner_slider1);
        slider.setAdapter(new MainSliderAdapter());
        slider.setInterval(4000);


        init();

        return view;
    }

    private void init() {
        rv_Category = view.findViewById(R.id.rv_Category);
        p_recycler=view.findViewById(R.id.p_recycler);
        rv_Category.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rv_Category.setAdapter(new CategoryAdapter(getCategory(),getContext()));

        p_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        p_recycler.setAdapter(new ProductAdapter(getContext()));
    }

    private List<Category> getCategory() {
        List<Category> list = new ArrayList<>();
        list.add(new Category("Man",R.drawable.man));
        list.add(new Category("Woman",R.drawable.woman));
        list.add(new Category("Boy",R.drawable.boy));
        list.add(new Category("Girl",R.drawable.girl));
        return list;
    }
}