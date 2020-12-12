package com.example.e_commerce_admin.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Cart;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.ui.activity.HomeActivity;
import com.example.e_commerce_admin.ui.activity.Order_Summary_Activity;
import com.example.e_commerce_admin.ui.adapter.CartAdapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.Loader;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

     private View view;
    private Loader loader;
    private int finalPrice = 0, mrpPrice = 0, discount = 0,qty=0;
    private CartAdapter cartAdapter;
    private RecyclerView review_recycler;
    private TextView tv_id_item, tv_totalrs, tv_discount, tv_dis_rs, tv_total_amnt, c_shopping;
    private Button b_checkout;
    private List<String> list = new ArrayList<>();

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);

        init();
        getlistno();


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        finalPrice = 0;
        mrpPrice = 0;
        discount = 0;
        qty=0;

        cartnapshot();

    }

    private void cartnapshot() {
        FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.Cart.key)
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {



                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            Product Product = dataSnapshot.child(FirebaseConstants.Cart.Product).getValue(Product.class);
                            int qtt = Integer.parseInt(dataSnapshot.child(FirebaseConstants.Cart.quantity).getValue() + "");
                            int dis = Integer.parseInt(dataSnapshot.child(FirebaseConstants.Cart.discount).getValue() + "");

                            Log.i("fdfddd", "onDataChange: "+qtt);
                            qty=qty+qtt ;

                            Log.i("fdfddd", "onDataChange: "+qty);
                            Log.i("fdfddd", "onDataChange: "+finalPrice);

                            finalPrice = finalPrice + qtt*Integer.parseInt( Product.getSelling_price());
                            Log.i("dgfgfgi", "onDataChange: "+finalPrice);

                            mrpPrice = mrpPrice + qtt*Integer.parseInt(Product.getMrp_price());
                            Log.i("dgfrghfg", "onDataChange: "+finalPrice);

                            discount = discount + dis;

                            Log.i("fdfeee", "onDataChange: "+qtt);
                            tv_id_item.setText("Items "+qty+"");

                        }

                        tv_total_amnt.setText("₹"+finalPrice+"");
                        tv_totalrs.setText("₹"+mrpPrice+"");
                        tv_dis_rs.setText("₹"+discount+"");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void init() {
        ((HomeActivity) getActivity()).setCheckedNavigationItem(1);
        review_recycler = view.findViewById(R.id.review_recycler);
        tv_id_item = view.findViewById(R.id.tv_id_item);
        tv_totalrs = view.findViewById(R.id.tv_totalrs);
        tv_discount = view.findViewById(R.id.tv_discount);
        tv_dis_rs = view.findViewById(R.id.tv_dis_rs);
        tv_total_amnt = view.findViewById(R.id.tv_total_amnt);
        c_shopping = view.findViewById(R.id.c_shopping);
        b_checkout = view.findViewById(R.id.b_checkout);

        loader = new Loader(getContext());


        b_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Order_Summary_Activity.class);
                startActivity(intent);
            }
        });



        review_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        FirebaseRecyclerOptions<Cart> option2 =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.Cart.key)
                                .child(FirebaseAuth.getInstance().getUid()), Cart.class)
                        .build();

        cartAdapter = new CartAdapter(option2, list, getContext(), new CartAdapter.ClickCallBack() {
            @Override
            public void click(String newQty, Cart model) {

                int updtqty=Integer.parseInt(newQty);
                int qty= (model.getQuantity());

                int esctqty = updtqty-qty;

                CartFragment.this.qty= CartFragment.this.qty+esctqty;
                int a=esctqty * Integer.parseInt(model.getProduct().getSelling_price())+finalPrice;
                finalPrice = a;
                tv_total_amnt.setText(a+"" );

                int c=esctqty * Integer.parseInt(model.getDiscount());

                discount =c;


                int b=esctqty * Integer.parseInt(model.getProduct().getMrp_price());


                tv_id_item.setText("Items "+CartFragment.this.qty+"");

                tv_totalrs.setText(b+mrpPrice+"");
                Log.i("vfgfhfg", "click: "+mrpPrice);
                mrpPrice=b+mrpPrice;

            }
        });
        review_recycler.setAdapter(cartAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        cartAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        cartAdapter.stopListening();
    }

    public void getlistno() {
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");

    }
}