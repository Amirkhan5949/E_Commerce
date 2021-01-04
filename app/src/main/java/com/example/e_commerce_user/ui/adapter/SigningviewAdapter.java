package com.example.e_commerce_user.ui.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.e_commerce_user.ui.fragment.SIGNINFragment;
import com.example.e_commerce_user.ui.fragment.SIGNUPFragment;

public class SigningviewAdapter extends FragmentPagerAdapter {

    public SigningviewAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    public SigningviewAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new SIGNINFragment();

            case 1:
                return new SIGNUPFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "SIGN IN";
            case 1:
                return "SIGN UP";

            default:

                return null;
        }
    }
}


