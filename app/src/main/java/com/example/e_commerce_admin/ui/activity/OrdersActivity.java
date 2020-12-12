package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Order;
import com.example.e_commerce_admin.model.SuperCategory;
import com.example.e_commerce_admin.ui.adapter.Order_Adapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class OrdersActivity extends AppCompatActivity {

    RecyclerView order_recycler;
    private Order_Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        order_recycler=findViewById(R.id.order_recycler);

        order_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        FirebaseRecyclerOptions<Order> options=
        new FirebaseRecyclerOptions.Builder<Order>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Order.key), Order.class)
                .build();

        adapter=new Order_Adapter(options,this);
        order_recycler.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}