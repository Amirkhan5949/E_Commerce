package com.example.e_commerce_user.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Brand;
import com.example.e_commerce_user.ui.adapter.BrandListAdapter;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BrandActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private int position;
     private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        init();


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

                        FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.Brand.key)
                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        List<Brand> brands=new ArrayList<>();

                                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                                            Brand value = dataSnapshot.getValue(Brand.class);
                                            brands.add(value);

                                        }


                                        viewPager.setAdapter(new BrandListAdapter(getSupportFragmentManager(),brands));
                                        tabLayout.setupWithViewPager(viewPager);
                                        viewPager.setCurrentItem(position);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });



    }

    private void init() {
        viewPager= findViewById(R.id.viewPager);

        tabLayout= findViewById(R.id.tabLayout);
        iv_back= findViewById(R.id.iv_back);
        toolbar= findViewById(R.id.toolbar);
        position =getIntent().getIntExtra("brandid",0);

    }
}