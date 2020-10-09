package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.GridSpacingItemDecoration;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.ui.adapter.ProductAdapter;
import com.example.e_commerce_admin.ui.adapter.ProductGridAdapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    RecyclerView recycler_wishlist;
    private ProductGridAdapter wishlistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        recycler_wishlist=findViewById(R.id.recycler_wishlist);

        recycler_wishlist.setLayoutManager(new GridLayoutManager(this,2));
        recycler_wishlist.addItemDecoration(new GridSpacingItemDecoration(2, util.dpToPx(recycler_wishlist.getContext(),0),true));
        FirebaseRecyclerOptions<Product> option2 =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.Product.key), Product.class)
                        .build();

        wishlistAdapter=new ProductGridAdapter(option2);
        recycler_wishlist.setAdapter(wishlistAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        wishlistAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        wishlistAdapter.stopListening();
    }

 }