package com.example.e_commerce_admin.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.airbnb.lottie.utils.Utils;
import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.State;
import com.example.e_commerce_admin.ui.adapter.State_Adapter;
import com.example.e_commerce_admin.utils.util;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class Address_EditActivity extends AppCompatActivity {

    TextView et_state,et_city;
    RadioButton rb1,rb2;
    private List<State>list=new ArrayList<>();
    private State_Adapter stateAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address__edit);

        et_state=findViewById(R.id.et_state);
        et_city=findViewById(R.id.et_city);
        rb1=findViewById(R.id.rb1);
        rb2=findViewById(R.id.rb2);

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