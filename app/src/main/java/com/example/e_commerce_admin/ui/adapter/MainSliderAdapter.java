package com.example.e_commerce_admin.ui.adapter;

import androidx.annotation.NonNull;

import com.example.e_commerce_admin.model.Banner;
import com.example.e_commerce_admin.model.Images;
import com.example.e_commerce_admin.model.SuperCategory;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {
    List<Images>list;

    public MainSliderAdapter(List<Images>list) {
        this.list=list;
     }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        viewHolder.bindImageSlide(list.get(position).getImg());
    }
}
