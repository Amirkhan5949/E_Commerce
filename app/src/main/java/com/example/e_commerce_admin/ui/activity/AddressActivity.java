package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.adapter.All_Address_Adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddressActivity extends AppCompatActivity {

    RecyclerView alladress_recycler;

    FloatingActionButton floating_action_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        alladress_recycler=findViewById(R.id.alladress_recycler);
        floating_action_button=findViewById(R.id.floating_action_button);

        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(AddressActivity.this,Address_EditActivity.class);
                startActivity(intent);
            }
        });


        alladress_recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        alladress_recycler.setAdapter(new All_Address_Adapter());

    }
}