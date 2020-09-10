package com.example.e_commerce_admin.utils;

import android.app.Application;

import ss.com.bannerslider.Slider;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Slider.init(new PicassoImageLoadingService(this));
    }
}
