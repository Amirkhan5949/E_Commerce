package com.example.e_commerce_user.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.e_commerce_user.model.Brand;
import com.example.e_commerce_user.ui.fragment.BrandsFragment;


import java.util.List;

public class BrandListAdapter extends FragmentPagerAdapter {
    private List<Brand> brands;




    public BrandListAdapter(@NonNull FragmentManager fm, List<Brand> brands) {
        super(fm);
        this.brands=brands;

     }


    public BrandListAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return  BrandsFragment.newInstance(brands.get(position).getBrand_id());

    }

    @Override
    public int getCount() {
        return brands.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return brands.get(position).getName();

    }



}
