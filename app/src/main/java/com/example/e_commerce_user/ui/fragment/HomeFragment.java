package com.example.e_commerce_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Banner;
import com.example.e_commerce_user.model.Brand;
import com.example.e_commerce_user.model.Product;
import com.example.e_commerce_user.model.SuperCategory;
import com.example.e_commerce_user.ui.activity.HomeActivity;
import com.example.e_commerce_user.ui.activity.SearchActivity;
import com.example.e_commerce_user.ui.activity.SubCategoryActivity;
import com.example.e_commerce_user.ui.adapter.BrandAdapter;
import com.example.e_commerce_user.ui.adapter.CategoryAdapter;
import com.example.e_commerce_user.ui.adapter.MainSliderAdapter;
import com.example.e_commerce_user.ui.adapter.ProductAdapter;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.Slider;

public class HomeFragment extends Fragment {
    final DatabaseReference base = FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.SuperCategory.key);
    private View view;
    private RecyclerView rv_Category, p_recycler, brand_recycler, recomded_recycler;
    private BrandAdapter brandAdapter;
    private CategoryAdapter adapter;
    private Slider slider;
    private TextView tv_viewall;
    private LinearLayout ll_main,ll_search;
    private ProductAdapter productAdapter;
    private ProgressBar progress;
    private ProductAdapter recomdedAdapter;
    private int TOTAL_API_CALL = 5 , CURRENT_API_CALL;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for getContext() fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ((HomeActivity) getActivity()).setCheckedNavigationItem(0);


        slider = view.findViewById(R.id.banner_slider1);


        init();
        getBanner();

        ll_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), SearchActivity.class));

            }
        });

        tv_viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                p_recycler.startLayoutAnimation();
                startActivity(new Intent(getContext(), SubCategoryActivity.class));
            }
        });

        return view;
    }

    private void init() {
        rv_Category = view.findViewById(R.id.rv_Category);
        tv_viewall = view.findViewById(R.id.tv_viewall);
        ll_main = view.findViewById(R.id.ll_main);
        p_recycler = view.findViewById(R.id.p_recycler);
        brand_recycler = view.findViewById(R.id.brandrecycler);
        recomded_recycler = view.findViewById(R.id.recommanded_recycler);
        progress = view.findViewById(R.id.progress);
        ll_search = view.findViewById(R.id.ll_search);


        FirebaseRecyclerOptions<Product> option =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.ProductRecommendedList.key)
                                .child("1")
                                .child(FirebaseConstants.ProductRecommendedList.Product), Product.class)
                        .build();

        recomdedAdapter = new ProductAdapter(option, getContext(), new ProductAdapter.ClickCallBack() {
            @Override
            public void load() {
                showUi();
            }
        });
        recomded_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recomded_recycler.setAdapter(recomdedAdapter);


        recomded_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recomded_recycler.setAdapter(recomdedAdapter);


        brand_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        FirebaseRecyclerOptions<Brand> opt =
                new FirebaseRecyclerOptions.Builder<Brand>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Brand.key), Brand.class)
                        .build();

        brandAdapter = new BrandAdapter(opt, new BrandAdapter.ClickCallBack() {
            @Override
            public void load() {
                showUi();
            }
        });
        brand_recycler.setAdapter(brandAdapter);


        rv_Category.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        FirebaseRecyclerOptions<SuperCategory> options =
                new FirebaseRecyclerOptions.Builder<SuperCategory>()
                        .setQuery(base, SuperCategory.class)
                        .build();

        adapter = new CategoryAdapter(options, new CategoryAdapter.ClickCallBack() {
            @Override
            public void load() {
                showUi();
            }
        });
        rv_Category.setAdapter(adapter);


        p_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        FirebaseRecyclerOptions<Product> option2 =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.ProductRecommendedList.key)
                                .child("2")
                                .child(FirebaseConstants.ProductRecommendedList.Product), Product.class)
                        .build();

        productAdapter = new ProductAdapter(option2, getContext(), new ProductAdapter.ClickCallBack() {
            @Override
            public void load() {
                showUi();
            }
        });
        p_recycler.setAdapter(productAdapter);


    }



    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
        productAdapter.startListening();
        brandAdapter.startListening();
        recomdedAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
        brandAdapter.stopListening();
        productAdapter.stopListening();
        recomdedAdapter.stopListening();
    }

    private void getBanner() {

        FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.Banner_Slider.key)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Banner> list;
                        list = new ArrayList<>();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                            list.add(dataSnapshot.getValue(Banner.class));
                        Log.i("rrgrgf", "onDataChange: " + list.toString());
                        slider.setAdapter(new MainSliderAdapter(list));
                        slider.setInterval(4000);
                        showUi();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        showUi();
                    }
                });

    }
    private void showUi() {
        CURRENT_API_CALL++;
        if(CURRENT_API_CALL==TOTAL_API_CALL){
            ll_main.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
//            new Handler().postDelayed(() -> {
//                p_recycler.startLayoutAnimation();
//                 recomded_recycler.startLayoutAnimation();
//                rv_Category .startLayoutAnimation();
//                brand_recycler.startLayoutAnimation();
//            },500);
        }
    }
}