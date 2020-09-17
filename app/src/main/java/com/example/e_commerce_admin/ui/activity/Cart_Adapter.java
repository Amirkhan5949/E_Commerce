package com.example.e_commerce_admin.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.Cart_Adapter_View> {

    @NonNull
    @Override
    public Cart_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart, parent, false);
        return new Cart_Adapter_View(view);    }

    @Override
    public void onBindViewHolder(@NonNull Cart_Adapter_View holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class Cart_Adapter_View extends RecyclerView.ViewHolder {
        public Cart_Adapter_View(@NonNull View itemView) {
            super(itemView);
        }
    }
}
