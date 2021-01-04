package com.example.e_commerce_user.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.e_commerce_user.ui.fragment.A_Fragment;
import com.example.e_commerce_user.ui.fragment.B_Fragment;
import com.example.e_commerce_user.ui.fragment.C_Fragment;

public class ViewpageAdapter extends FragmentPagerAdapter {
    public ViewpageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new A_Fragment();

            case 1:
                return  new B_Fragment();

            case 2:
                return new C_Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
