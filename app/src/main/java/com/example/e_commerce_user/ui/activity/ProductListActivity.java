package com.example.e_commerce_user.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.GridSpacingItemDecoration;
import com.example.e_commerce_user.model.Product;
import com.example.e_commerce_user.ui.adapter.WishListAdapter;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.example.e_commerce_user.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView rv_productlist;
    private ImageView iv_back;
    private ProgressBar progress;
    private WishListAdapter adapter;
    private String supercatid  = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        init();

        rv_productlist.setLayoutManager(new GridLayoutManager(this,2));
        rv_productlist.addItemDecoration(new GridSpacingItemDecoration(2, util.dpToPx(rv_productlist.getContext(),0),true));

        Query query = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.Product.key)
                .orderByChild(FirebaseConstants.Product.category_id)
                .equalTo(supercatid);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("dggdgdv", "onDataChange: "+snapshot.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("dggdgdv", "onCancelled: "+ error.getMessage());
            }
        });


        FirebaseRecyclerOptions<Product> option =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(query, Product.class)
                        .build();

        adapter=new WishListAdapter(option,progress);
        rv_productlist.setAdapter(adapter);


    }

    private void init() {
        rv_productlist=findViewById(R.id.rv_productlist);
        iv_back=findViewById(R.id.iv_back);
        progress=findViewById(R.id.progress);
        supercatid=getIntent().getStringExtra("supercatid");
        Log.i("sfdfdff", "init: "+supercatid);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}