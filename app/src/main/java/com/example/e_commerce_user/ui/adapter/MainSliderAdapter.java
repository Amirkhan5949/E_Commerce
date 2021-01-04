package com.example.e_commerce_user.ui.adapter;

import com.example.e_commerce_user.model.Banner;
import com.example.e_commerce_user.model.Images;

import java.util.List;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {
    List<Banner>list;
    List<Images> images;

    public MainSliderAdapter(List<Images> images,String s) {
        this.images = images;
    }

    public MainSliderAdapter(List<Banner>list) {
        this.list=list;
    }



    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }else {
            return images.size();
        }

    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        if (list != null) {
            viewHolder.bindImageSlide(list.get(position).getImage());
        }
        else {
            viewHolder.bindImageSlide(images.get(position).getImg());
        }


    }
}
