package com.example.e_commerce_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.ui.activity.AddressActivity;
import com.example.e_commerce_user.ui.activity.CartActivity;
import com.example.e_commerce_user.ui.activity.HomeActivity;
import com.example.e_commerce_user.ui.activity.MainActivity;
import com.example.e_commerce_user.ui.activity.OrdersActivity;
import com.example.e_commerce_user.ui.activity.ProfileActivity;
import com.example.e_commerce_user.ui.activity.WishlistActivity;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.example.e_commerce_user.utils.Loader;
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
    private LinearLayout ll_main;
    private ProgressBar progress;
    private CircleImageView iv_profile_image;
    private TextView profile, orders, cart, wishlist, address,
            notification, policy, share, logout, tv_name, tv_emial_id;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu, container, false);
        ((HomeActivity) getActivity()).setCheckedNavigationItem(3);

        init();

        if (FirebaseAuth.getInstance().getUid() != null) {
            FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.User.key)
                    .child(FirebaseAuth.getInstance().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child("email").getValue(String.class) != null) {
                                ll_main.setVisibility(View.VISIBLE);
                                progress.setVisibility(View.GONE);
                                Log.i("ffdsffc", "onDataChange: "+snapshot.toString());
                                tv_emial_id.setText(snapshot.child("email").getValue(String.class));
                            } else {
                                tv_emial_id.setText("xyz123@gmail.com");
                            }
                            if (snapshot.child("Username").getValue(String.class) != null) {
                                tv_name.setText(snapshot.child("Username").getValue(String.class));

                            } else {
                                tv_name.setText("xyz");
                            }
                            Picasso.get().load(snapshot.child("image").getValue(String.class))
                                    .placeholder(R.drawable.userpic).into(iv_profile_image);
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loader.show();
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(logout.getContext(), MainActivity.class);
                logout.getContext().startActivity(intent);
                loader.dismiss();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FirebaseAuth.getInstance().getUid() != null) {
                    Intent intent = new Intent(getActivity(), ProfileActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getContext(), MainActivity.class));
                }

            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FirebaseAuth.getInstance().getUid() != null) {
                    Intent intent = new Intent(getActivity(), CartActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getContext(), MainActivity.class));
                }

            }
        });
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FirebaseAuth.getInstance().getUid() != null) {
                    Intent intent = new Intent(getActivity(), OrdersActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getContext(), MainActivity.class));

                }

            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FirebaseAuth.getInstance().getUid() != null) {
                    Intent intent = new Intent(getActivity(), AddressActivity.class);
                    startActivity(intent); }
                else {
                    startActivity(new Intent(getContext(), MainActivity.class));

                }

            }
        });

        wishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FirebaseAuth.getInstance().getUid() != null) {
                    Intent intent = new Intent(getActivity(), WishlistActivity.class);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getContext(), MainActivity.class));
                }


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

        iv_profile_image = view.findViewById(R.id.iv_profile_image);
        ll_main = view.findViewById(R.id.ll_main);
        progress = view.findViewById(R.id.progress);
        tv_name = view.findViewById(R.id.tv_name);
        tv_emial_id = view.findViewById(R.id.tv_emial_id);
        loader = new Loader(getContext());
        profile = view.findViewById(R.id.p_profile);
        orders = view.findViewById(R.id.orders);
        cart = view.findViewById(R.id.cart);
        wishlist = view.findViewById(R.id.wishlist);
        address = view.findViewById(R.id.address);
        notification = view.findViewById(R.id.notification);
        policy = view.findViewById(R.id.policy);
        share = view.findViewById(R.id.share);
        logout = view.findViewById(R.id.logout);

    }
}