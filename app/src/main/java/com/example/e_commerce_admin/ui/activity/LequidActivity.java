package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;

import android.os.Bundle;

import com.cuberto.liquid_swipe.LiquidPager;
import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.adapter.ViewpageAdapter;

public class LequidActivity extends AppCompatActivity {

    private LiquidPager pager;
    private ViewpageAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lequid);
        pager=findViewById(R.id.pager);
        adapter=new ViewpageAdapter(getSupportFragmentManager(),1);
        pager.setAdapter(adapter);
    }
}