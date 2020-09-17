package com.example.e_commerce_admin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.activity.AddressActivity;
import com.example.e_commerce_admin.ui.activity.CartActivity;
import com.example.e_commerce_admin.ui.activity.OrdersActivity;
import com.example.e_commerce_admin.ui.activity.WishlistActivity;

public class MenuFragment extends Fragment {

    View view;
    TextView profile,orders,cart,wishlist,address,notification,policy,share,logout;

    public MenuFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_menu, container, false);
        profile=view.findViewById(R.id.p_profile);
        orders=view.findViewById(R.id.orders);
        cart=view.findViewById(R.id.cart);
        wishlist=view.findViewById(R.id.wishlist);
        address=view.findViewById(R.id.address);
        notification=view.findViewById(R.id.notification);
        policy=view.findViewById(R.id.policy);
        share=view.findViewById(R.id.share);
        logout=view.findViewById(R.id.logout);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), OrdersActivity.class);
                startActivity(intent);
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
            }
        });

        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),  WishlistActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}