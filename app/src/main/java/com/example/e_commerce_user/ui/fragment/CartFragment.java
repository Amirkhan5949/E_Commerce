package com.example.e_commerce_user.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Cart;
import com.example.e_commerce_user.model.Product;
import com.example.e_commerce_user.ui.activity.Order_Summary_Activity;
import com.example.e_commerce_user.ui.adapter.CartAdapter;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.example.e_commerce_user.utils.Loader;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {

    private LinearLayout ll_Main;
    private View view;
    private Loader loader;
    private ProgressBar progress ;
    private Toolbar tb_tool;
    private int finalPrice = 0, mrpPrice = 0, discount = 0,qty=0;
    private CartAdapter cartAdapter;
    private RecyclerView review_recycler;
    private TextView tv_id_item, tv_totalrs, tv_discount, tv_dis_rs, tv_total_amnt, c_shopping;
    private Button b_checkout;
    private String type;
    private List<String> list = new ArrayList<>();

    private int TOTAL_API_CALL = 2,CURRENT_API_CALL;

    public static CartFragment newInstance(String type) {
        CartFragment f = new CartFragment();
        Bundle args = new Bundle();
        args.putString("type",type);
        f.setArguments(args);
        return f;
    }

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);

        init();
        load();
        getlistno();

        Bundle args = getArguments();
        if (args!=null){
            type=args.getString("type") ;

            if (type.equals("FromActivity")){
                tb_tool.setVisibility(View.GONE);
            }

                Log.i("dsfdfdg", "onCreateView: "+type);
        }



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

    private void load() {
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

            @Override
            public void remove(Cart model ,String id) {

                qty=qty-model.getQuantity();
                finalPrice=finalPrice-model.getQuantity()*Integer.parseInt(model.getProduct().getSelling_price());
                mrpPrice=mrpPrice-model.getQuantity()*Integer.parseInt(model.getProduct().getMrp_price());


                FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Cart.key)
                        .child(FirebaseAuth.getInstance().getUid())
                        .child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        tv_total_amnt.setText(finalPrice+"");
                        tv_totalrs.setText(mrpPrice+"");
                        tv_id_item.setText("Items "+qty);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }

            @Override
            public void load() {
                showUi();
            }
        });
        review_recycler.setAdapter(cartAdapter);


    }

    private void showUi() {
        CURRENT_API_CALL++;
        if(CURRENT_API_CALL==TOTAL_API_CALL){
            ll_Main.setVisibility(View.VISIBLE);
            progress.setVisibility(View.GONE);
        }
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
                            int dis = Integer.parseInt(dataSnapshot.child(FirebaseConstants.Cart.discount).getValue() + "");
                            discount = discount + dis;

                            int qtt = Integer.parseInt(dataSnapshot.child(FirebaseConstants.Cart.quantity).getValue() + "");
                            qty=qty+qtt ;

                            finalPrice = finalPrice + qtt*Integer.parseInt( Product.getSelling_price());
                            mrpPrice = mrpPrice + qtt*Integer.parseInt(Product.getMrp_price());
                            tv_id_item.setText("Items "+qty+"");
                        }

                        tv_total_amnt.setText("₹"+finalPrice+"");
                        tv_totalrs.setText("₹"+mrpPrice+"");
                        tv_dis_rs.setText("₹"+discount+"");

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        showUi();
                    }
                });

    }

    private void init() {
//        ((HomeActivity) getActivity()).setCheckedNavigationItem(1);
        review_recycler = view.findViewById(R.id.review_recycler);
        tv_id_item = view.findViewById(R.id.tv_id_item);
        progress = view.findViewById(R.id.progress);
        tb_tool = view.findViewById(R.id.tb_tool);
        tv_totalrs = view.findViewById(R.id.tv_totalrs);
        tv_discount = view.findViewById(R.id.tv_discount);
        tv_dis_rs = view.findViewById(R.id.tv_dis_rs);
        tv_total_amnt = view.findViewById(R.id.tv_total_amnt);
        c_shopping = view.findViewById(R.id.c_shopping);
        b_checkout = view.findViewById(R.id.b_checkout);
        ll_Main = view.findViewById(R.id.ll_Main);

        loader = new Loader(getContext());


        b_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), Order_Summary_Activity.class);
                startActivity(intent);


            }
        });



        review_recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


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