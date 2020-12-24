package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.GridSpacingItemDecoration;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.ui.adapter.WishListAdapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



public class WishlistActivity extends AppCompatActivity {

    private RecyclerView recycler_wishlist;
    private WishListAdapter wishlistAdapter;
    private ImageView iv_back;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

       init();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recycler_wishlist.setLayoutManager(new GridLayoutManager(this,2));
        recycler_wishlist.addItemDecoration(new GridSpacingItemDecoration(2, util.dpToPx(recycler_wishlist.getContext(),0),true));
        if (FirebaseAuth.getInstance().getUid()!=null){

            FirebaseRecyclerOptions<Product> option2 =
                    new FirebaseRecyclerOptions.Builder<Product>()
                            .setQuery(FirebaseDatabase.getInstance().getReference()
                                    .child(FirebaseConstants.WishList.key)
                                    .child(FirebaseAuth.getInstance().getUid()), Product.class)
                            .build();

            wishlistAdapter=new WishListAdapter(option2,progress);
            recycler_wishlist.setAdapter(wishlistAdapter);


        }
        else {
            Intent intent=new Intent(this,HomeActivity.class);
            startActivity(intent);
        }

    }

    private void init() {
        recycler_wishlist=findViewById(R.id.recycler_wishlist);
        iv_back=findViewById(R.id.iv_back);
       progress=findViewById(R.id.progress);
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