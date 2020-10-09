package com.example.e_commerce_admin.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.ui.activity.OrderDetailActivity;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.Cart_Adapter_View> {

    @NonNull
    @Override
    public Cart_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart, parent, false);
        return new Cart_Adapter_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Cart_Adapter_View holder, int position) {

        holder.ll_order_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.ll_order_detail.getContext(), OrderDetailActivity.class);
                holder.ll_order_detail.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class Cart_Adapter_View extends RecyclerView.ViewHolder {
        LinearLayout ll_order_detail;
        public Cart_Adapter_View(@NonNull View itemView) {
            super(itemView);
            ll_order_detail=itemView.findViewById(R.id.ll_order_detail);
        }
    }
}
