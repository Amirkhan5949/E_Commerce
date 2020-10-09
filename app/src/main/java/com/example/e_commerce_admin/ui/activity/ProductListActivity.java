package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.RecoverySystem;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.GridSpacingItemDecoration;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.ui.adapter.ProductGridAdapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView rv_p_list;
    private ProductGridAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        rv_p_list=findViewById(R.id.rv_p_list);

        rv_p_list.setLayoutManager(new GridLayoutManager(this,2));
        rv_p_list.addItemDecoration(new GridSpacingItemDecoration(2, util.dpToPx(this,0),true));


        FirebaseRecyclerOptions<Product> option2 =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.Product.key), Product.class)
                        .build();

        adapter=new ProductGridAdapter(option2);
        rv_p_list.setAdapter(adapter);

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