package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.adapter.Order_Adapter;
import com.example.e_commerce_admin.ui.adapter.Order_Summary_Adapter;

public class Order_Summary_Activity extends AppCompatActivity {

    RecyclerView rv_order_summary;
    TextView tv_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__summary_);

        rv_order_summary=findViewById(R.id.rv_order_summary);
        tv_continue=findViewById(R.id.tv_continue);

        tv_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(tv_continue.getContext(),PaymentActivity.class);
                startActivity(intent);
            }
        });

        rv_order_summary.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_order_summary.setAdapter(new Order_Summary_Adapter());
    }
}