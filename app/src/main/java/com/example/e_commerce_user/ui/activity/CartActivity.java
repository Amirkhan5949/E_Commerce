package com.example.e_commerce_user.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.ui.fragment.CartFragment;

public class CartActivity extends AppCompatActivity {

   private FrameLayout fl_frame;
   private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

       init();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void init() {
        fl_frame=findViewById(R.id.fl_frame);
        iv_back=findViewById(R.id.iv_back);
        replace(  CartFragment.newInstance("FromActivity"));
    }

    public void replace(Fragment fragment){
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_frame,fragment);
        ft.commit();
    }



}