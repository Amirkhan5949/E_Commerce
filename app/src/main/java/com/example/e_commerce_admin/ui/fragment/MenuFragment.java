package com.example.e_commerce_admin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.activity.AddressActivity;
import com.example.e_commerce_admin.ui.activity.CartActivity;
import com.example.e_commerce_admin.ui.activity.HomeActivity;
import com.example.e_commerce_admin.ui.activity.MainActivity;
import com.example.e_commerce_admin.ui.activity.OrdersActivity;
import com.example.e_commerce_admin.ui.activity.ProfileActivity;
import com.example.e_commerce_admin.ui.activity.WishlistActivity;
import com.example.e_commerce_admin.utils.Loader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MenuFragment extends Fragment {

    private View view;
    private Loader loader;
    private CircleImageView iv_profile_image;
    private TextView profile,orders,cart,wishlist,address,
            notification,policy,share,logout,tv_name,tv_emial_id;

    public MenuFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_menu, container, false);
        ((HomeActivity)getActivity()).setCheckedNavigationItem(3);

        init();

        FirebaseDatabase.getInstance().getReference().child("Admin")
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        tv_emial_id.setText(snapshot.child("email").getValue(String.class));
                        tv_name.setText(snapshot.child("name").getValue(String.class));
                        Picasso.get().load(snapshot.child("image").getValue(String.class)).into(iv_profile_image);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loader.show();
                Intent intent=new Intent(logout.getContext(),MainActivity.class);
                logout.getContext().startActivity(intent);
                loader.dismiss();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

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

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "My E_Commarce Application daeveloped by Aamir";
                String shareSub = "Your subject here";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share using"));
            }
        });

        return view;
    }

    private void init() {

        iv_profile_image=view.findViewById(R.id.iv_profile_image);
        tv_name=view.findViewById(R.id.tv_name);
        tv_emial_id=view.findViewById(R.id.tv_emial_id);
        loader=new Loader(getContext());
        profile=view.findViewById(R.id.p_profile);
        orders=view.findViewById(R.id.orders);
        cart=view.findViewById(R.id.cart);
        wishlist=view.findViewById(R.id.wishlist);
        address=view.findViewById(R.id.address);
        notification=view.findViewById(R.id.notification);
        policy=view.findViewById(R.id.policy);
        share=view.findViewById(R.id.share);
        logout=view.findViewById(R.id.logout);

    }
}