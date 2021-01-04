package com.example.e_commerce_user.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.e_commerce_user.model.SuperCategory;
import com.example.e_commerce_user.ui.fragment.ManFragment;

import java.util.List;

public class Super_Cat_viewAdapter extends FragmentPagerAdapter {
   private List<SuperCategory> superCategories;
    public Super_Cat_viewAdapter(@NonNull FragmentManager fm, List<SuperCategory> superCategories) {
        super(fm);
        this.superCategories=superCategories;
    }

    public Super_Cat_viewAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return  ManFragment.newInstance(superCategories.get(position).getSuper_category_id());
    }

    @Override
    public int getCount() {
        return superCategories.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return superCategories.get(position).getName();

    }
}
