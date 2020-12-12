package com.example.e_commerce_admin.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Cart;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentActivity extends AppCompatActivity {

    private View progress;
    private TextView tv_continue;
    private LinearLayout ll_Main;
    private List<Cart> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tv_continue=findViewById(R.id.tv_continue);
        progress=findViewById(R.id.progress);
        ll_Main=findViewById(R.id.ll_Main);

        progress.setVisibility(View.VISIBLE);
        ll_Main.setVisibility(View.GONE);

        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Cart.key)
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ll_Main.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.GONE);
                        list.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            list.add(dataSnapshot.getValue(Cart.class));
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        ll_Main.setVisibility(View.VISIBLE);
                        progress.setVisibility(View.GONE);
                    }
                });

        tv_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase.getInstance().getReference().child("Admin")
                        .child(FirebaseAuth.getInstance().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                if (snapshot.child("default_address").getValue()==null)
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                Map<String,Object> objectMap = new HashMap<>();

                DatabaseReference order = FirebaseDatabase.getInstance().getReference().child("Order");

                for (Cart cart : list) {
                    DatabaseReference push = order.push();

                    Map<String,Object> map = new HashMap<>();

                    map.put("user_id",FirebaseAuth.getInstance().getUid());
                    map.put(FirebaseConstants.Order.mrp_price,cart.getProduct().getMrp_price());
                    map.put(FirebaseConstants.Order.selling_price,cart.getProduct().getSelling_price());
                    map.put(FirebaseConstants.Order.ordered_mrp_price,cart.getProduct().getMrp_price());
                    map.put(FirebaseConstants.Order.ordered_selling_price,cart.getProduct().getSelling_price());
                    map.put(FirebaseConstants.Order.quantity,cart.getQuantity());
                    map.put(FirebaseConstants.Order.payment_type,"COD");
                    map.put(FirebaseConstants.Order.order_status,"Confirm");
                    map.put(FirebaseConstants.Order.address,"Shamshabad");
                    map.put(FirebaseConstants.Order.Product,cart.getProduct());
                    map.put(FirebaseConstants.Order.time, ServerValue.TIMESTAMP);
//                    map.put(FirebaseConstants.Order.default_address, );


                    objectMap.put(push.getKey(),map);

                }

                order.updateChildren(objectMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.i("sfsfs", "onComplete: "+task.isSuccessful());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("sfsfs", "onFailure: "+e.getMessage());
                    }
                });



            }
        });
    }
}