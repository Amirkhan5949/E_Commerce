package com.example.e_commerce_admin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.utils.util;

public class Super_cat_Adapter extends RecyclerView.Adapter<Super_cat_Adapter.Super_cat_Viewholder> {
    Context context;

    public Super_cat_Adapter(Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public Super_cat_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.super_cat, parent, false);

        return new Super_cat_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Super_cat_Viewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class Super_cat_Viewholder extends RecyclerView.ViewHolder {
        public Super_cat_Viewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
