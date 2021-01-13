package com.example.e_commerce_user.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.cuberto.liquid_swipe.LiquidPager;
import com.example.e_commerce_user.R;
import com.example.e_commerce_user.ui.adapter.ViewpageAdapter;

public class LequidActivity extends AppCompatActivity {

    private LiquidPager pager;
    private ViewpageAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lequid);

        pager=findViewById(R.id.pager);
        adapter=new ViewpageAdapter(getSupportFragmentManager(),1);
        pager.setAdapter(adapter);

    }
}