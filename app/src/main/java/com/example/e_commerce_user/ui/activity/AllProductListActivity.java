package com.example.e_commerce_user.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.GridSpacingItemDecoration;
import com.example.e_commerce_user.model.Product;
import com.example.e_commerce_user.ui.adapter.WishListAdapter;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.example.e_commerce_user.utils.Loader;
import com.example.e_commerce_user.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllProductListActivity extends AppCompatActivity {

    private RecyclerView rv_productlist;
    private ProgressBar progressBar;
    private ImageView iv_back;
    private WishListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_product_list);

        init();
        rv_productlist.setLayoutManager(new GridLayoutManager(this,2));
        rv_productlist.addItemDecoration(new GridSpacingItemDecoration(2, util.dpToPx(rv_productlist.getContext(),0),true));



        DatabaseReference query = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.Product.key);
        FirebaseRecyclerOptions<Product> options=
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(query, Product.class)
                        .build();

        adapter=new WishListAdapter(options,progressBar,count -> {

        });
        rv_productlist.setAdapter(adapter);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private void init() {
        iv_back=findViewById(R.id.iv_back);
        rv_productlist=findViewById(R.id.rv_productlist);
         progressBar=findViewById(R.id.progress);
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