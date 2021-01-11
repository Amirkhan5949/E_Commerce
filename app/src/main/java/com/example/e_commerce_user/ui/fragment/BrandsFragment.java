package com.example.e_commerce_user.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.GridSpacingItemDecoration;
import com.example.e_commerce_user.model.Product;
import com.example.e_commerce_user.ui.adapter.WishListAdapter;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.example.e_commerce_user.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BrandsFragment extends Fragment {
    private View view;
    private RecyclerView rv_brands;
    private ProgressBar progress;
    private WishListAdapter adapter;
    private String id, brandid;
    private TextView tv_data;


    public BrandsFragment() {
        // Required empty public constructor
    }

    public static BrandsFragment newInstance(String brandid) {
        BrandsFragment fragment = new BrandsFragment();
        Bundle args = new Bundle();
        args.putString("brandid", brandid);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_brands, container, false);

        init();

        rv_brands.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rv_brands.addItemDecoration(new GridSpacingItemDecoration(2, util.dpToPx(getContext(), 0), true));

        Bundle args = getArguments();
        if (args != null) {
            brandid = args.getString("brandid");
        }

        FirebaseRecyclerOptions<Product> option =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.Product.key)
                                .orderByChild(FirebaseConstants.Product.brand_id)
                                .equalTo(brandid), Product.class)
                        .build();

        adapter = new WishListAdapter(option, progress, new WishListAdapter.ClickCallBack() {
            @Override
            public void click(int count) {
                if (count==0){
                    tv_data.setVisibility(View.VISIBLE);
                }
            }
        });
        rv_brands.setAdapter(adapter);
        return view;
    }

    private void init() {
        rv_brands = view.findViewById(R.id.rv_brands);
        tv_data = view.findViewById(R.id.tv_data);
        progress = view.findViewById(R.id.progress);
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