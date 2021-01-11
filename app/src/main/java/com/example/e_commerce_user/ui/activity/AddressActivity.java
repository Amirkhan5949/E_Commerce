package com.example.e_commerce_user.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Address;
import com.example.e_commerce_user.ui.adapter.All_Address_Adapter;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.example.e_commerce_user.utils.Loader;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddressActivity extends AppCompatActivity {

   private ProgressBar progress;
   private RecyclerView alladress_recycler;
   private All_Address_Adapter adapter;
   private ImageView iv_back;

   private Loader  loader;
   private FloatingActionButton floating_action_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


       init();

        loader=new Loader(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(AddressActivity.this,Address_EditActivity.class);
                intent.putExtra("ComeFrom","Float");
                intent.putExtra("type","float");
                startActivity(intent);
            }
        });


        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.User.key)
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Log.i("sfdgyd", "onDataChange: "+snapshot.toString());

                       String add_type= (snapshot.child("default_address_index").getValue(String.class));

                        alladress_recycler.setLayoutManager(new LinearLayoutManager(alladress_recycler.getContext(),LinearLayoutManager.VERTICAL,false));

                        FirebaseRecyclerOptions<Address> option =
                                new FirebaseRecyclerOptions.Builder<Address>()
                                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Address")
                                                        .child(FirebaseAuth.getInstance().getUid()),
                                                Address.class)
                                        .build();

                        adapter = new All_Address_Adapter(option,add_type==null?"":add_type,progress);
                        alladress_recycler.setAdapter(adapter);
                        adapter.startListening();


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void init() {
        alladress_recycler=findViewById(R.id.alladress_recycler);
        iv_back=findViewById(R.id.iv_back);
        floating_action_button=findViewById(R.id.floating_action_button);

        progress = findViewById(R.id.progress);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();

    }
}