package com.example.e_commerce_admin.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.UploadRequest;
import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Address;
import com.example.e_commerce_admin.model.Color;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.model.TimeLineModel;
import com.example.e_commerce_admin.ui.adapter.Color_Adapter;
import com.example.e_commerce_admin.ui.adapter.TimeLineAdapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.ImagePickerHelper;
import com.example.e_commerce_admin.utils.PermissionHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myhexaville.smartimagepicker.ImagePicker;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.e_commerce_admin.utils.PermissionHelper.setUpPermission;


public class OrderDetailActivity extends AppCompatActivity {

    private Product product;
    private Address address;
    private ImagePicker imagePicker;
    private RecyclerView rv_shipping;
    private List<TimeLineModel> list=new ArrayList();



    ImageView iv_back,iv_image,iv_expandqty;
    TextView tv_cod,tv_order_rs,tv_name,tv_description,tv_size,
            tv_quantity,tv_sellingp,tv_mrp,tv_help,tv_cancel,tv_user_name,
            tv_user_add,tv_user_mobno,tv_p_item,tv_amnt,tv_rs_dis,tv_total_amnt;
    View colorview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        init();


        rv_shipping.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        rv_shipping.setAdapter(new TimeLineAdapter(list));



        String order_id=getIntent().getStringExtra("order_id");

        FirebaseDatabase.getInstance().getReference().child("Order")
                .child(order_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        address=snapshot.child(FirebaseConstants.Order.Address).getValue(Address.class);
                            product=snapshot.child(FirebaseConstants.Order.Product).getValue(Product.class);

                            tv_name.setText(product.getName());
                        tv_order_rs.setText(product.getSelling_price());
                            tv_sellingp.setText(product.getSelling_price());
                            tv_mrp.setText(product.getMrp_price());
                            tv_amnt.setText(product.getSelling_price());
                        tv_cod.setText(snapshot.child(FirebaseConstants.Order.payment_type).getValue(String.class));
                            tv_user_add.setText(address.getAddress()+","+address.getLandmark()+","+address.getCity()+","+address.getState()+","+address.getPincode());
                            tv_description.setText(product.getDetails());
                            int qtt = Integer.parseInt(snapshot.child(FirebaseConstants.Order.quantity).getValue() + "");

                        tv_p_item.setText("Items :"+qtt);
                        tv_total_amnt.setText(product.getSelling_price());
                            String status=snapshot.child(FirebaseConstants.Order.order_status).getValue(String.class);

                        adddata(status);


                        Log.i("dsdfsf", "onDataChange: "+product.getImg());
                            Log.i("dsdfsf", "onDataChange: "+product.getDetails());
//                            tv_quantity.setText(qtt);
                            Picasso.get().load(product.getImg()).into(iv_image);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

    }

    private void init() {
        iv_back=findViewById(R.id.iv_back);
        rv_shipping=findViewById(R.id.rv_shipping);
        iv_image=findViewById(R.id.iv_image);
        iv_expandqty=findViewById(R.id.iv_expandqty);
        tv_cod=findViewById(R.id.tv_cod);
        tv_order_rs=findViewById(R.id.tv_order_rs);
        tv_name=findViewById(R.id.tv_name);
        tv_description=findViewById(R.id.tv_description);
        tv_size=findViewById(R.id.tv_size);
        tv_quantity=findViewById(R.id.tv_quantity);
        tv_sellingp=findViewById(R.id.tv_sellingp);
        tv_mrp=findViewById(R.id.tv_mrp);
        tv_help=findViewById(R.id.tv_help);
        tv_cancel=findViewById(R.id.tv_cancel);
        tv_user_name=findViewById(R.id.tv_user_name);
        tv_user_add=findViewById(R.id.tv_user_add);
        tv_user_mobno=findViewById(R.id.tv_user_mobno);
        tv_p_item=findViewById(R.id.tv_p_item);
        tv_amnt=findViewById(R.id.tv_amnt);
        tv_rs_dis=findViewById(R.id.tv_rs_dis);
        tv_total_amnt=findViewById(R.id.tv_total_amnt);
        colorview=findViewById(R.id.colorview);

    }



    private void adddata(String status){

        switch (status){
            case "Confirm":
                list.add(new TimeLineModel("Confirm","",1));
                list.add(new TimeLineModel("Packed","",0));
                list.add(new TimeLineModel("Shipped","",0));
                list.add(new TimeLineModel("Delivered","",0));
                break;

            case "Packed":
                list.add(new TimeLineModel("Confirm","",1));
                list.add(new TimeLineModel("Packed","",1));
                list.add(new TimeLineModel("Shipped","",0));
                list.add(new TimeLineModel("Delivered","",0));
                break;

            case "Shipped":
                list.add(new TimeLineModel("Confirm","",1));
                list.add(new TimeLineModel("Packed","",1));
                list.add(new TimeLineModel("Shipped","",1));
                list.add(new TimeLineModel("Delivered","",0));
                break;

            case "Delivered":
                list.add(new TimeLineModel("Confirm","",1));
                list.add(new TimeLineModel("Packed","",1));
                list.add(new TimeLineModel("Shipped","",1));
                list.add(new TimeLineModel("Delivered","",1));
                break;

            case "Cancel":
                list.add(new TimeLineModel("Confirm","",1));
                list.add(new TimeLineModel("Cancel","",-1));
                break;
        }


    }


    }


//        loader.show();
//        if(imageRequest!=null){
//        imageRequest.callback(new UploadCallback() {
//            @Override
//            public void onStart(String requestId) {
//                // your code here
//            }


