package com.example.e_commerce_admin.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Banner;
import com.example.e_commerce_admin.model.Brand;
import com.example.e_commerce_admin.model.Category;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.model.SuperCategory;
import com.example.e_commerce_admin.ui.activity.HomeActivity;
import com.example.e_commerce_admin.ui.adapter.BrandAdapter;
import com.example.e_commerce_admin.ui.adapter.CategoryAdapter;
import com.example.e_commerce_admin.ui.adapter.ProductAdapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
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
    private ProductAdapter productAdapter;
    private ProductAdapter recomdedAdapter;

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

        return view;
    }

    private void init() {
        rv_Category = view.findViewById(R.id.rv_Category);
        p_recycler = view.findViewById(R.id.p_recycler);
        brand_recycler = view.findViewById(R.id.brandrecycler);
        recomded_recycler = view.findViewById(R.id.recommanded_recycler);


        FirebaseRecyclerOptions<Product> option =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.ProductRecommendedList.key)
                                .child("1")
                                .child(FirebaseConstants.ProductRecommendedList.Product), Product.class)
                        .build();

        recomdedAdapter = new ProductAdapter(option, getContext());
        recomded_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recomded_recycler.setAdapter(recomdedAdapter);


        recomded_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recomded_recycler.setAdapter(recomdedAdapter);


        brand_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        FirebaseRecyclerOptions<Brand> opt =
                new FirebaseRecyclerOptions.Builder<Brand>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Brand.key), Brand.class)
                        .build();

        brandAdapter = new BrandAdapter(opt);
        brand_recycler.setAdapter(brandAdapter);


        rv_Category.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        FirebaseRecyclerOptions<SuperCategory> options =
                new FirebaseRecyclerOptions.Builder<SuperCategory>()
                        .setQuery(base, SuperCategory.class)
                        .build();

        adapter = new CategoryAdapter(options);
        rv_Category.setAdapter(adapter);


        p_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        FirebaseRecyclerOptions<Product> option2 =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.ProductRecommendedList.key)
                                .child("2")
                                .child(FirebaseConstants.ProductRecommendedList.Product), Product.class)
                        .build();

        productAdapter = new ProductAdapter(option2, getContext());
        p_recycler.setAdapter(productAdapter);


    }

    private List<Category> getCategory() {
        List<Category> list = new ArrayList<>();
        list.add(new Category("Man", R.drawable.man));
        list.add(new Category("Woman", R.drawable.woman));
        list.add(new Category("Boy", R.drawable.boy));
        list.add(new Category("Girl", R.drawable.girl));
        return list;
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
//                        slider.setAdapter(new MainSliderAdapter(list));
//                        slider.setInterval(4000);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

}