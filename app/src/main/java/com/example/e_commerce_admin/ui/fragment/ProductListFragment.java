package com.example.e_commerce_admin.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.GridSpacingItemDecoration;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.ui.activity.HomeActivity;
import com.example.e_commerce_admin.ui.adapter.ProductGridAdapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.util;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ProductListFragment extends Fragment {


    private String id;
    private View view;
    private ImageView iv_back;
    private RecyclerView rv_p_list;
    private ProductGridAdapter adapter;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance(String id){
        ProductListFragment f = new ProductListFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        f.setArguments(args);
        Log.i("sdfryvdgf", "newInstance: "+id);
        return f;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_product_list, container, false);


        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
            Log.i("fsfsfs", "onCreateView: " + id);
        }

        rv_p_list=view.findViewById(R.id.rv_p_list);
        iv_back=view.findViewById(R.id.iv_back);




        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity)getActivity()).onBackPressed();
            }
        });
        rv_p_list.setLayoutManager(new GridLayoutManager(getContext(),2));
        rv_p_list.addItemDecoration(new GridSpacingItemDecoration(2, util.dpToPx(getActivity(),0),true));

        FirebaseRecyclerOptions<Product> option2 =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.Product.key)
                                .orderByChild(FirebaseConstants.Product.category_id).equalTo(id), Product.class)
                        .build();

        adapter=new ProductGridAdapter(option2);
        rv_p_list.setAdapter(adapter);
        return view;
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