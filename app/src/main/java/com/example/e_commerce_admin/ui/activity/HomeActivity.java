package com.example.e_commerce_admin.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.fragment.CartFragment;
import com.example.e_commerce_admin.ui.fragment.CategoryFragment;
import com.example.e_commerce_admin.ui.fragment.HomeFragment;
import com.example.e_commerce_admin.ui.fragment.MenuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);



        frameLayout=findViewById(R.id.frame);
        bottomNavigationView=findViewById(R.id.bottom);



        replace(new HomeFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        replace(new HomeFragment(),"HomeFragment");
                        return true;
                    case R.id.cart:
                        replace( CartFragment.newInstance("FromFregment"),"CartFragment");
                        return true;

                    case R.id.category:
                        replace(  CategoryFragment.newInstance("","direct", 0),"CategoryFragment");
                        return true;

                    case R.id.menu:
                        replace(new MenuFragment(),"MenuFragment");
                        return true;

                }
                return false;
            }
        });

    }

    public void replace(Fragment fragment){
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame,fragment);
        ft.commit();
    }

    public void replace(Fragment fragment,String tag){
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame,fragment);
        ft.addToBackStack(tag);
        ft.commit();
    }

    public void setCheckedNavigationItem(int i){
        bottomNavigationView.getMenu().getItem(i).setChecked(true);
    }
}