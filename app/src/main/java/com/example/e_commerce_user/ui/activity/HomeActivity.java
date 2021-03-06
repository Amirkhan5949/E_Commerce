package com.example.e_commerce_user.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;


import com.example.e_commerce_user.R;
import com.example.e_commerce_user.ui.fragment.CartFragment;
import com.example.e_commerce_user.ui.fragment.CategoryFragment;
import com.example.e_commerce_user.ui.fragment.HomeFragment;
import com.example.e_commerce_user.ui.fragment.MenuFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {


    private FrameLayout frameLayout;
    private BottomNavigationView bottomNavigationView;


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
                        if (FirebaseAuth.getInstance().getUid()!=null){
                            replace( CartFragment.newInstance("FromFregment"),"CartFragment");
                        }
                        else {
                            startActivity(new Intent(HomeActivity.this,MainActivity.class));
                        }
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