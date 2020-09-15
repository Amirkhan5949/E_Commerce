package com.example.e_commerce_admin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Category;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.ui.activity.WishlistActivity;
import com.example.e_commerce_admin.ui.adapter.BrandAdapter;
import com.example.e_commerce_admin.ui.adapter.CategoryAdapter;
import com.example.e_commerce_admin.ui.adapter.MainSliderAdapter;
import com.example.e_commerce_admin.ui.adapter.ProductAdapter;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class HomeFragment extends Fragment {
     View view;
     private RecyclerView rv_Category,p_recycler,brand_recycler,recomded_recycler;




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
        brand_recycler=view.findViewById(R.id.brandrecycler);
        recomded_recycler=view.findViewById(R.id.recommanded_recycler);

        brand_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        brand_recycler.setAdapter(new BrandAdapter());


        recomded_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
       recomded_recycler.setAdapter(new ProductAdapter(getproduct()

       ));

        rv_Category.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rv_Category.setAdapter(new CategoryAdapter(getCategory(),getContext()));

        p_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        p_recycler.setAdapter(new ProductAdapter(getproduct()));


    }

    private List<Category> getCategory() {
        List<Category> list = new ArrayList<>();
        list.add(new Category("Man",R.drawable.man));
        list.add(new Category("Woman",R.drawable.woman));
        list.add(new Category("Boy",R.drawable.boy));
        list.add(new Category("Girl",R.drawable.girl));
        return list;
    }

    private List<Product> getproduct(){
        List<Product> list = new ArrayList<>();
        list.add(new Product("Aamir",R.drawable.aamir));
        list.add(new Product("Avani",R.drawable.whatapp));
        list.add(new Product("neha",R.drawable.back1));
        list.add(new Product( "PrintOctopus Women's",R.drawable.nike));
        list.add(new Product(" Inkast Denim Co. Women's",R.drawable.red));
        list.add(new Product("Offbeat Women's Graphic",R.drawable.black));
        list.add(new Product("Frog",R.drawable.din));
        list.add(new Product("Bikni",R.drawable.dress));
        list.add(new Product("chaddi[",R.drawable.pink));
        return list;
    }
}