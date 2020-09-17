package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.adapter.MainSliderAdapter;
import com.example.e_commerce_admin.ui.adapter.ProductReview_Adapter;

import ss.com.bannerslider.Slider;

public class ProductDetailActivity extends AppCompatActivity {
    Slider banner_slider;

    RecyclerView rv_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        banner_slider=findViewById(R.id.banner_slider);
        banner_slider.setAdapter(new MainSliderAdapter());
        banner_slider.setInterval(4000);

        rv_review=findViewById(R.id.rv_review);
        rv_review.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_review.setAdapter(new ProductReview_Adapter());
    }
}