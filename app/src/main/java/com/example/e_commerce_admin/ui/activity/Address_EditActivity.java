package com.example.e_commerce_admin.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.utils.Utils;
import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.State;
import com.example.e_commerce_admin.ui.adapter.State_Adapter;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Address_EditActivity extends AppCompatActivity {

    private TextView et_state,et_city;
    private RadioButton rb1,rb2;
    private Button btn_save;
    private EditText et_pincode,et_address,et_landmark,et_name,et_mobno;
    private List<State>list=new ArrayList<>();
    private State_Adapter stateAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address__edit);

        btn_save=findViewById(R.id.btn_save);
        et_state=findViewById(R.id.et_state);
        et_city=findViewById(R.id.et_city);
        et_pincode=findViewById(R.id.et_pincode);
        et_address=findViewById(R.id.et_address);
        et_landmark=findViewById(R.id.et_landmark);
        et_name=findViewById(R.id.et_name);
        et_mobno=findViewById(R.id.et_mobno);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);




        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map = new HashMap<>();
                map.put(FirebaseConstants.Address.pincode,et_pincode.getText().toString());
                map.put(FirebaseConstants.Address.address,et_address.getText().toString());
                map.put(FirebaseConstants.Address.state,et_state.getText().toString());
                map.put(FirebaseConstants.Address.city,et_city.getText().toString());
                map.put(FirebaseConstants.Address.landmark,et_landmark.getText().toString());
                map.put(FirebaseConstants.Address.name,et_name.getText().toString());
                map.put(FirebaseConstants.Address.mob_no,et_mobno.getText().toString());
                if (rb1.isChecked()){
                    map.put(FirebaseConstants.Address.address_type,"work address");
                }else map.put(FirebaseConstants.Address.address_type,"home address");

                Log.i("dgfhffg", "onClick: "+map.toString());

                FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Address.key)
                        .child(FirebaseAuth.getInstance().getUid())
                        .push()
                        .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        Toast.makeText(Address_EditActivity.this, "Address Registered successfully", Toast.LENGTH_SHORT).show();

                        String ComeFrom=getIntent().getStringExtra("ComeFrom");
                        if (ComeFrom.equals("OrderSummary")){
                            Intent intent=new Intent(Address_EditActivity.this,Order_Summary_Activity.class);
                            startActivity(intent);
                        }
                        else {
                            finish();
                        }




                        Log.i("sfeddgd", "onComplete: "+task.isSuccessful());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("sfeddgd", "onFailure: "+e.getMessage());
                    }
                });

            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rb2.isChecked()) {
                    rb2.setChecked(false);
                }
            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rb1.isChecked()) {
                    rb1.setChecked(false);
                }

            }
        });


        et_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final DialogPlus dialog = DialogPlus.newDialog(et_state.getContext())
                        .setContentHolder(new ViewHolder(R.layout.edit_city))
                        .setCancelable(false)
                        .setGravity(Gravity.CENTER)
                        .setExpanded(false)
                        .setMargin(util.dpToPx(et_state.getContext(),20),util.dpToPx(et_state.getContext(),150),util.dpToPx(et_state.getContext(),20),util.dpToPx(et_state.getContext(),150))
                        .create();
                dialog.show();

                View viewholder = dialog.getHolderView();

                RecyclerView rv_city = viewholder.findViewById(R.id.rv_city);
                getList();
                rv_city.setLayoutManager(new LinearLayoutManager(et_state.getContext(),LinearLayoutManager.VERTICAL,false));
                stateAdapter=new State_Adapter(list, new State_Adapter.ClickCallBack() {
                    @Override
                    public void click(int i) {
                        et_city.setText(list.get(i).getState());
                        dialog.dismiss();

                    }
                });
                rv_city.setAdapter(stateAdapter);

            }
        });


        et_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialog = DialogPlus.newDialog(et_state.getContext())
                        .setContentHolder(new ViewHolder(R.layout.edit_state))
                        .setCancelable(false)
                        .setGravity(Gravity.CENTER)
                        .setExpanded(false)
                        .setMargin(util.dpToPx(et_state.getContext(),20),util.dpToPx(et_state.getContext(),150),util.dpToPx(et_state.getContext(),20),util.dpToPx(et_state.getContext(),150))
                        .create();
                dialog.show();

                View viewholder = dialog.getHolderView();

                RecyclerView rv_state = viewholder.findViewById(R.id.rv_state);
                getList();
                rv_state.setLayoutManager(new LinearLayoutManager(et_state.getContext(),LinearLayoutManager.VERTICAL,false));
             stateAdapter=new State_Adapter(list, new State_Adapter.ClickCallBack() {
                 @Override
                 public void click(int i) {
                     et_state.setText(list.get(i).getState());
                     dialog.dismiss();

                 }
             });
             rv_state.setAdapter(stateAdapter);

            }
        });


    }

    List<State>getList(){
        list.add(new State("Small"));
        list.add(new State("Large"));
        list.add(new State("Medium"));
        list.add(new State("Extra Large"));
        list.add(new State("Double Extra Large"));
        list.add(new State("Small"));
        list.add(new State("Large"));
        list.add(new State("Medium"));
        list.add(new State("Extra Large"));
        list.add(new State("Double Extra Large"));
        list.add(new State("Small"));
        list.add(new State("Large"));
        list.add(new State("Medium"));
        list.add(new State("Extra Large"));
        list.add(new State("Double Extra Large"));
        return list;

    }
}