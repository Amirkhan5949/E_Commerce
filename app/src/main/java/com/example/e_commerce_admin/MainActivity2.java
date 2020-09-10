package com.example.e_commerce_admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        ImageView imageView = findViewById(R.id.img);
        RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(this)
                .load("https://assets.materialup.com/uploads/76d63bbc-54a1-450a-a462-d90056be881b/preview.png")
                .placeholder(R.drawable.camera)
                .error(R.drawable.camera)
                .apply(requestOptions)
                .into(imageView);
    }
}