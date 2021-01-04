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
import com.example.e_commerce_user.model.SuperCategory;
import com.example.e_commerce_user.ui.adapter.HomeSuperCategoryAdapter;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.example.e_commerce_user.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class SubCategoryActivity extends AppCompatActivity {

    private RecyclerView rv_subcat;
    private ImageView iv_back;
    private HomeSuperCategoryAdapter adapter;
    private ProgressBar progress ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);

        init();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rv_subcat.setLayoutManager(new GridLayoutManager(this,2));
        rv_subcat.addItemDecoration(new GridSpacingItemDecoration(2, util.dpToPx(rv_subcat.getContext(),15),true));


        FirebaseRecyclerOptions<SuperCategory> options =
                new FirebaseRecyclerOptions.Builder<SuperCategory>()
                        .setQuery( FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.SuperCategory.key), SuperCategory.class)
                        .build();
        adapter=new HomeSuperCategoryAdapter(options,progress);
        rv_subcat.setAdapter(adapter);



    }

    private void init() {
        rv_subcat=findViewById(R.id.rv_subcat);
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