package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Order;
import com.example.e_commerce_admin.model.SuperCategory;
import com.example.e_commerce_admin.ui.adapter.Order_Adapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OrdersActivity extends AppCompatActivity {

    private RecyclerView order_recycler;
    private Order_Adapter adapter;
    private ProgressBar progress;
    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

       init();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        order_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        Query query = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.Order.key)
                .orderByChild(FirebaseConstants.Order.user_id)
                .equalTo(FirebaseAuth.getInstance().getUid());

        FirebaseRecyclerOptions<Order> options=
        new FirebaseRecyclerOptions.Builder<Order>()
                .setQuery(query, Order.class)
                .build();

        adapter=new Order_Adapter(options,this,progress);
        order_recycler.setAdapter(adapter);

    }

    private void init() {
        order_recycler=findViewById(R.id.order_recycler);
        iv_back=findViewById(R.id.iv_back);
        progress=findViewById(R.id.progress);
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