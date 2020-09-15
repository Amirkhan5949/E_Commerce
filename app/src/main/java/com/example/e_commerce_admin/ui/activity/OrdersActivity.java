package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.adapter.Order_Adapter;

public class OrdersActivity extends AppCompatActivity {

    RecyclerView order_recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        order_recycler=findViewById(R.id.order_recycler);

        order_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        order_recycler.setAdapter(new Order_Adapter());
    }
}