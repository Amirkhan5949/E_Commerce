package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.fragment.CategoryFragment;
import com.example.e_commerce_admin.ui.fragment.HomeFragment;

public class OverCatActivity extends AppCompatActivity {

    private FrameLayout frame;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_cat);

        init();

        int position= getIntent().getIntExtra("position",-1);
        String id= getIntent().getStringExtra("id");
        String type="Fromadapter";

        replace(CategoryFragment.newInstance(id,type,position));


    }

    private void init() {
        frame=findViewById(R.id.frame);
        iv_back=findViewById(R.id.iv_back);
    }

    public void replace(Fragment fragment){
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame,fragment);
        ft.commit();
    }
}