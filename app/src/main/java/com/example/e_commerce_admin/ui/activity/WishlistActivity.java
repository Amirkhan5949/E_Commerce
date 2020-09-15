package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.GridSpacingItemDecoration;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.ui.adapter.ProductAdapter;
import com.example.e_commerce_admin.ui.adapter.ProductGridAdapter;
import com.example.e_commerce_admin.utils.util;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    RecyclerView recycler_wishlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        recycler_wishlist=findViewById(R.id.recycler_wishlist);

        recycler_wishlist.setLayoutManager(new GridLayoutManager(this,2));
        recycler_wishlist.addItemDecoration(new GridSpacingItemDecoration(2, util.dpToPx(recycler_wishlist.getContext(),0),true));

        recycler_wishlist.setAdapter(new ProductGridAdapter(getproduct()));
    }

    private List<Product> getproduct(){
        List<Product> list = new ArrayList<>();
        list.add(new Product("Aamir",R.drawable.aamir));
        list.add(new Product("Avani",R.drawable.whatapp));
        list.add(new Product("neha",R.drawable.back1));
        list.add(new Product( "PrintOctopus Women's",R.drawable.nike));
        list.add(new Product(" Inkast Denim Co. Women's",R.drawable.red));
        list.add(new Product("Offbeat Women's Graphic",R.drawable.black));
        list.add(new Product("Frog",R.drawable.din));
        list.add(new Product("Bikni",R.drawable.dress));
        list.add(new Product("chaddi[",R.drawable.pink));
        return list;
    }
}