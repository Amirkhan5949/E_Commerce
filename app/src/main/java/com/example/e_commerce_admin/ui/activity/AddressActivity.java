package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.adapter.All_Address_Adapter;

public class AddressActivity extends AppCompatActivity {

    RecyclerView alladress_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        alladress_recycler=findViewById(R.id.alladress_recycler);

        alladress_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        alladress_recycler.setAdapter(new All_Address_Adapter());

    }
}