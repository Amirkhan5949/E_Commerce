package com.example.e_commerce_user.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Address;
import com.example.e_commerce_user.model.Cart;
import com.example.e_commerce_user.model.notificationrequest.Notification;
import com.example.e_commerce_user.model.notificationrequest.NotificationBody;
import com.example.e_commerce_user.model.notificationresponse.NotificationResponse;
import com.example.e_commerce_user.network.NetworkUrl;
import com.example.e_commerce_user.network.NotificationRequest;
import com.example.e_commerce_user.network.RetrofitClient;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    private View progress;
    private TextView tv_continue;
    private LinearLayout ll_Main;
    private ImageView iv_back;
    private List<Cart> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        init();

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

                add_ress();
                startActivity(new Intent(PaymentActivity.this,HomeActivity.class));

            }
        });
    }

    private void init() {
        tv_continue=findViewById(R.id.tv_continue);
        progress=findViewById(R.id.progress);
        ll_Main=findViewById(R.id.ll_Main);
        iv_back=findViewById(R.id.iv_back);

        progress.setVisibility(View.VISIBLE);
        ll_Main.setVisibility(View.GONE);
    }

    private void order(Address address) {

        Map<String,Object> objectMap = new HashMap<>();

        DatabaseReference order = FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Order.key);

        for (Cart cart : list) {
            DatabaseReference push = order.push();

            Map<String,Object> map = new HashMap<>();

            map.put("user_id",FirebaseAuth.getInstance().getUid());
            map.put(FirebaseConstants.Order.mrp_price,cart.getProduct().getMrp_price());
            map.put(FirebaseConstants.Order.selling_price,cart.getProduct().getSelling_price());
            map.put(FirebaseConstants.Order.ordered_mrp_price,cart.getProduct().getMrp_price());
            map.put(FirebaseConstants.Order.ordered_selling_price,cart.getProduct().getSelling_price());
            map.put(FirebaseConstants.Order.quantity,cart.getQuantity());
            map.put(FirebaseConstants.Order.payment_type,"Cash On  Delivery");
            map.put(FirebaseConstants.Order.order_status,"Confirm");
             map.put(FirebaseConstants.Order.Product,cart.getProduct());
            map.put(FirebaseConstants.Order.time, ServerValue.TIMESTAMP);
            map.put(FirebaseConstants.Order.Address, address);


            objectMap.put(push.getKey(),map);

        }

        order.updateChildren(objectMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.i("sfsfs", "onComplete: "+task.isSuccessful());

                FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Cart.key)
                        .child(FirebaseAuth.getInstance().getUid())
                        .removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                         sentNotification();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("sfsfs", "onFailure: "+e.getMessage());
            }
        });

    }

    private void sentNotification() {

        NotificationBody body = new NotificationBody();
        body.setTo("/topics/"+ NetworkUrl.topic);
        Notification notification = new Notification();
        notification.setTitle("New Order");
        notification.setBody("You got new order.");
        body.setNotification(notification);

        RetrofitClient.getRetrofit(NetworkUrl.baseUrl).create(NotificationRequest.class)
                .sent(body)
                .enqueue(new Callback<NotificationResponse>() {
                    @Override
                    public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                        if (response != null) {
                            if (response.isSuccessful()) {
                                Toast.makeText(PaymentActivity.this, "Order done.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NotificationResponse> call, Throwable t) {
                        Toast.makeText(PaymentActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private void add_ress() {

        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.User.key)
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Log.i("sfdgyd", "onDataChange: "+snapshot.toString());

                        String add_type= (snapshot.child("default_address_index").getValue(String.class));

                        if (add_type==null){
                            FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Address.key)
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        long a=  snapshot.getChildrenCount();
                                            if (a!=0){
                                                Address address = snapshot.getChildren().iterator().next().getValue(Address.class);
                                                order(address);
                                                Log.i("fdgdgd", "onDataChange: "+address.toString());
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                        }

                        else {
                            FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Address.key)
                                    .child(FirebaseAuth.getInstance().getUid())
                                    .child(add_type)
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            Address add=snapshot.getValue(Address.class);
                                            order(add);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}