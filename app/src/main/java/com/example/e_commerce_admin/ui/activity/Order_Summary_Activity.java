package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.adapter.Order_Adapter;
import com.example.e_commerce_admin.ui.adapter.Order_Summary_Adapter;

public class Order_Summary_Activity extends AppCompatActivity {

    RecyclerView rv_order_summary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__summary_);

        rv_order_summary=findViewById(R.id.rv_order_summary);

        rv_order_summary.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_order_summary.setAdapter(new Order_Summary_Adapter());
    }
}