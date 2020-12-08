package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.adapter.Cart_Adapter;

public class CartActivity extends AppCompatActivity {

   private RecyclerView rv_cart;                                                                    
   private TextView tv_place_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rv_cart=findViewById(R.id.rv_cart);
        tv_place_order=findViewById(R.id.tv_place_order);

        tv_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tv_place_order.getContext(),Order_Summary_Activity.class);
                startActivity(intent);
            }
        });

        rv_cart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_cart.setAdapter(new Cart_Adapter());
    }
}