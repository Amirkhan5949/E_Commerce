package com.example.e_commerce_admin.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Cart;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.ui.adapter.CartAdapter;
import com.example.e_commerce_admin.ui.adapter.Cart_Adapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {

   private RecyclerView rv_cart;
   private CartAdapter cartAdapter;
    private int finalPrice = 0, mrpPrice = 0, discount = 0,qty=0;

    private TextView tv_place_order,tv_id_item,tv_totalrs,tv_discount,tv_dis_rs
           ,tv_total_amnt,tv_totl_rs,tv_view_price;
    private List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

     init();
     getlistno();
        cartnapshot();

        tv_place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tv_place_order.getContext(),Order_Summary_Activity.class);
                startActivity(intent);
            }
        });

        rv_cart.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        FirebaseRecyclerOptions<Cart> option2 =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.Cart.key)
                                .child(FirebaseAuth.getInstance().getUid()), Cart.class)
                        .build();


        cartAdapter=(new CartAdapter(option2, list, this, new CartAdapter.ClickCallBack() {
            @Override
            public void click(String newQty, Cart model) {

            }
        }));
        rv_cart.setAdapter(cartAdapter);
    }

    private void init() {
        rv_cart=findViewById(R.id.rv_cart);
        tv_place_order=findViewById(R.id.tv_place_order);
        tv_id_item=findViewById(R.id.tv_id_item);
        tv_totalrs=findViewById(R.id.tv_totalrs);
        tv_discount=findViewById(R.id.tv_discount);
        tv_dis_rs=findViewById(R.id.tv_dis_rs);
        tv_total_amnt=findViewById(R.id.tv_total_amnt);
        tv_totl_rs=findViewById(R.id.tv_totl_rs);
        tv_view_price=findViewById(R.id.tv_view_price);

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
                        tv_totl_rs.setText("₹"+finalPrice+"");
                        tv_totalrs.setText("₹"+mrpPrice+"");
                        tv_dis_rs.setText("₹"+discount+"");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

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