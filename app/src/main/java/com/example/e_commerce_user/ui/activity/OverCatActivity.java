package com.example.e_commerce_user.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.ui.fragment.CategoryFragment;

public class OverCatActivity extends AppCompatActivity {

    private FrameLayout frame;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over_cat);

        init();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int position= getIntent().getIntExtra("position",-1);
        String id= getIntent().getStringExtra("id");
        String type="Fromadapter";

        Log.i("esdwdwd", "act: "+position);
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