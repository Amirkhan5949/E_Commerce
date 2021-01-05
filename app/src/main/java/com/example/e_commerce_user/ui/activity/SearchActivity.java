package com.example.e_commerce_user.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView rv_productlist;
    private ProgressBar progressBar;
    private ImageView iv_back,iv_search;
    private WishListAdapter adapter;
    private EditText et_search;
    private Loader loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();

        rv_productlist.setLayoutManager(new GridLayoutManager(this,2));
        rv_productlist.addItemDecoration(new GridSpacingItemDecoration(2, util.dpToPx(rv_productlist.getContext(),0),true));


//        Query query = FirebaseDatabase.getInstance().getReference()
//                .child(FirebaseConstants.Product.key)
//                .orderByChild(FirebaseConstants.Product.category_id)
//                .equalTo(supercatid);
//
//        FirebaseRecyclerOptions<Product> option =
//                new FirebaseRecyclerOptions.Builder<Product>()
//                        .setQuery(query, Product.class)
//                        .build();
//
//        adapter=new WishListAdapter(option,progressBar);
//        rv_productlist.setAdapter(adapter);

        DatabaseReference query = FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.Product.key);
        FirebaseRecyclerOptions<Product>options=
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(query, Product.class)
                        .build();

        adapter=new WishListAdapter(options,progressBar);
        rv_productlist.setAdapter(adapter);

        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loader.show();
                if(et_search.getText().toString().length()!=0){

                    Log.i("dscshdc", "onClick: "+et_search.getText().toString());
                    Query query = FirebaseDatabase.getInstance().getReference()
                            .child(FirebaseConstants.Product.key).orderByChild("name")
                            .startAt(et_search.getText().toString())
                            .endAt(et_search.getText().toString()+"\uf8ff");

                    FirebaseRecyclerOptions<Product>options=
                            new FirebaseRecyclerOptions.Builder<Product>()
                                    .setQuery(query, Product.class)
                                    .build();

                    adapter=new WishListAdapter(options,progressBar);
                    rv_productlist.setAdapter(adapter);
                    adapter.startListening();

                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            loader.dismiss();
                            Log.i("dscshdc", "onDataChange: "+snapshot.toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.i("dscshdc", "onDataChange: "+error.getMessage().toString());
                        }
                    });
                }
            }
        });



        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private void init() {
        iv_back=findViewById(R.id.iv_back);
        iv_search=findViewById(R.id.iv_search);
        loader=new Loader(this);
        rv_productlist=findViewById(R.id.rv_productlist);
        et_search=findViewById(R.id.et_search);
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