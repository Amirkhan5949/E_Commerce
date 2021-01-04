package com.example.e_commerce_user.utils;

import android.app.Application;

import com.cloudinary.android.MediaManager;

import ss.com.bannerslider.Slider;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MediaManager.init(this);
        Slider.init(new PicassoImageLoadingService(this));
    }
}
