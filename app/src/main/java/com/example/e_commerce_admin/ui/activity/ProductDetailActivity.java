package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Color;
import com.example.e_commerce_admin.ui.adapter.Color_Adapter;
import com.example.e_commerce_admin.ui.adapter.MainSliderAdapter;
import com.example.e_commerce_admin.ui.adapter.ProductReview_Adapter;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class ProductDetailActivity extends AppCompatActivity {
    Slider banner_slider;
    RecyclerView rv_color;

    RecyclerView rv_review;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        banner_slider=findViewById(R.id.banner_slider);

        rv_color=findViewById(R.id.rv_color);

        rv_color.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        rv_color.setAdapter(new Color_Adapter(getcolor()));

        banner_slider.setAdapter(new MainSliderAdapter());
        banner_slider.setInterval(4000);

        rv_review=findViewById(R.id.rv_review);
        rv_review.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_review.setAdapter(new ProductReview_Adapter());
    }

    List<Color> getcolor(){
        List<Color>list= new ArrayList<>();
        list.add(new Color("#000000"));
        list.add(new Color("#FF113F"));
        list.add(new Color("#FFFFFF"));
        list.add(new Color("#0019FE"));
        list.add(new Color("#00DC1D"));
        list.add(new Color("#FFC107"));
        list.add(new Color("#000000"));
        return list;
    }
}