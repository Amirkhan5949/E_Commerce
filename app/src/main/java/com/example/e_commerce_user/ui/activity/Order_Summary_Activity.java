package com.example.e_commerce_user.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Cart;
import com.example.e_commerce_user.model.Product;
import com.example.e_commerce_user.ui.adapter.CartAdapter;
import com.example.e_commerce_user.ui.adapter.Order_Summary_Adapter;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Order_Summary_Activity extends AppCompatActivity {

    private RecyclerView rv_order_summary;
    private CartAdapter cartAdapter;
    private ProgressBar progress;
    private ImageView iv_back;
    private LinearLayout ll_main;
    private int TOTAL_API_CALL = 3 , CURRENT_API_CALL;

    private Order_Summary_Adapter adapter;
    private TextView tv_id_item, tv_sellingp, tv_discount, tv_dis_rs,
            tv_total_amnt, tv_total_amnt_rs, tv_continue,
            tv_user_name, tv_add_type, tv_user_add, tv_mob_no;
    private Button btn_change_Add;

    private int finalPrice = 0, mrpPrice = 0, discount = 0, qty = 0;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__summary_);

        init();
        setListener();

        cartnapshot();
        getAddress();
        setCartAdapter();

    }

    private void setCartAdapter() {
        rv_order_summary.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        FirebaseRecyclerOptions<Cart> option2 =
                new FirebaseRecyclerOptions.Builder<Cart>()
                        .setQuery(FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.Cart.key)
                                .child(FirebaseAuth.getInstance().getUid()), Cart.class)
                        .build();

        cartAdapter = new CartAdapter(option2, list, this, new CartAdapter.ClickCallBack() {
            @Override
            public void click(String newQty, Cart model) {

                int updtqty = Integer.parseInt(newQty);
                int qty = (model.getQuantity());


                int esctqty = updtqty - qty;

                Order_Summary_Activity.this.qty = Order_Summary_Activity.this.qty + esctqty;
                int a = esctqty * Integer.parseInt(model.getProduct().getSelling_price()) + finalPrice;
                finalPrice = a;
                tv_total_amnt.setText(a + "");
                tv_total_amnt_rs.setText(a + "");

                int c = esctqty * Integer.parseInt(model.getDiscount());
                discount = c;

                int b = esctqty * Integer.parseInt(model.getProduct().getMrp_price());


                tv_id_item.setText("Items " + Order_Summary_Activity.this.qty + "");

                tv_sellingp.setText(b + mrpPrice + "");
                mrpPrice = b + mrpPrice;

            }

            @Override
            public void remove(Cart model, String id) {

            }

            @Override
            public void load() {
                showUi();
            }
        });
        rv_order_summary.setAdapter(cartAdapter);

    }

    private void getAddress() {
        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.User.key)
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String id=  snapshot.child("default_address_index").getValue(String.class);
                        if (id==null){
                            Log.i("sdbcjsdbc", "onDataChange: 1");
                            FirebaseDatabase.getInstance().getReference().child("Address")
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                            if (snapshot.getChildrenCount()!=0){
                                                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                                                    tv_user_name.setText(dataSnapshot1.child(FirebaseConstants.Address.name).getValue(String.class));
                                                    tv_add_type.setText(dataSnapshot1.child(FirebaseConstants.Address.address_type).getValue().toString());
                                                    String s = dataSnapshot1.child(FirebaseConstants.Address.address).getValue(String.class)+", "+
                                                            dataSnapshot1.child(FirebaseConstants.Address.landmark).getValue(String.class)+", "+
                                                            dataSnapshot1.child(FirebaseConstants.Address.city).getValue(String.class)+", "+
                                                            dataSnapshot1.child(FirebaseConstants.Address.state).getValue(String.class)+", "+
                                                            dataSnapshot1.child(FirebaseConstants.Address.pincode).getValue(String.class);
                                                    tv_user_add.setText(s);
                                                    tv_mob_no.setText(dataSnapshot1.child(FirebaseConstants.Address.mob_no).getValue().toString());
                                                    break;
                                                }
                                                showUi();
                                            }
                                            else {
                                                Intent intent=new Intent(Order_Summary_Activity.this,Address_EditActivity.class);
                                                intent.putExtra("ComeFrom","OrderSummary");
                                                intent.putExtra("type","float");
                                                startActivity(intent);
                                                finish();
                                            }



                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            showUi();
                                            Log.i("sdbcjsdbc", "onDataChange: 1111"+error.getMessage());
                                        }
                                    });
                        }
                        else {
                            Log.i("sdbcjsdbc", "onDataChange: 1");
                            FirebaseDatabase.getInstance().getReference().child("Address")
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .child(id)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Log.i("sdbcjsdbc", "onDataChange: 2");
                                            if (snapshot.exists()) {
                                                showUi();
                                                if(snapshot.getChildrenCount()==0){
                                                    Intent intent = new Intent(Order_Summary_Activity.this,AddressActivity.class);
                                                    startActivity(intent);
                                                }
                                                else {
                                                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                                                        tv_user_name.setText(dataSnapshot1.child(FirebaseConstants.Address.name).getValue(String.class));
                                                        tv_add_type.setText(dataSnapshot1.child(FirebaseConstants.Address.address_type).getValue().toString());
                                                        tv_user_add.setText(dataSnapshot1.child(FirebaseConstants.Address.address).getValue(String.class));
                                                        tv_mob_no.setText(dataSnapshot1.child(FirebaseConstants.Address.mob_no).getValue().toString());
                                                    }
                                                }

                                            }
                                            else {
                                                Intent intent = new Intent(Order_Summary_Activity.this,AddressActivity.class);
                                                startActivity(intent);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            showUi();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        showUi();
                    }
                });


    }

    private void setListener() {

        tv_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(tv_continue.getContext(), PaymentActivity.class);
                startActivity(intent);


                Intent intent1 = new Intent(getApplicationContext(),
                        PaymentActivity.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(view.findViewById(R.id.tv_continue), "transition_login");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
                {  ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Order_Summary_Activity.this, pairs);
                    startActivity(intent, options.toBundle()); }
                else { startActivity(intent1); }
            }
        });

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_change_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Order_Summary_Activity.this,AddressActivity.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cartnapshot();
    }

    private void init() {
        rv_order_summary = findViewById(R.id.rv_order_summary);
        tv_continue = findViewById(R.id.tv_continue);
        tv_id_item = findViewById(R.id.tv_id_item);
        iv_back = findViewById(R.id.iv_back);
        ll_main = findViewById(R.id.ll_main);
        progress = findViewById(R.id.progress);
        tv_sellingp = findViewById(R.id.tv_sellingp);
        tv_discount = findViewById(R.id.tv_discount);
        tv_dis_rs = findViewById(R.id.tv_dis_rs);
        tv_total_amnt = findViewById(R.id.tv_total_amnt);
        tv_total_amnt_rs = findViewById(R.id.tv_total_amnt_rs);
        btn_change_Add = findViewById(R.id.btn_change_Add);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_add_type = findViewById(R.id.tv_add_type);
        tv_user_add = findViewById(R.id.tv_user_add);
        tv_mob_no = findViewById(R.id.tv_mob_no);

        getlistno();
    }

    private void cartnapshot() {
        FirebaseDatabase.getInstance().getReference()
                .child(FirebaseConstants.Cart.key)
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        showUi();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                            Product Product = dataSnapshot.child(FirebaseConstants.Cart.Product).getValue(Product.class);
                            int qtt = Integer.parseInt(dataSnapshot.child(FirebaseConstants.Cart.quantity).getValue() + "");
                            int dis = Integer.parseInt(dataSnapshot.child(FirebaseConstants.Cart.discount).getValue() + "");

                            qty = qty + qtt;

                            finalPrice = finalPrice + qtt * Integer.parseInt(Product.getSelling_price());
                            mrpPrice = mrpPrice + qtt * Integer.parseInt(Product.getMrp_price());
                            discount = discount + dis;

                            tv_id_item.setText("Items " + qty + "");

                        }


                        tv_total_amnt_rs.setText("₹" + finalPrice + "");
                        tv_total_amnt.setText("₹" + finalPrice + "");
                        tv_sellingp.setText("₹" + mrpPrice + "");
                        tv_dis_rs.setText("₹" + discount + "");


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        showUi();
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

    private void showUi() {
        CURRENT_API_CALL++;

        Log.i("SDGHFCGHSD", "showUi: "+CURRENT_API_CALL);
        Log.i("SDGHFCGHSD", "showUi: "+TOTAL_API_CALL);

        if(CURRENT_API_CALL==TOTAL_API_CALL){
            ll_main.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
        }
    }


}







