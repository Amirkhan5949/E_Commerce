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

public class Order_Adapter extends RecyclerView.Adapter<Order_Adapter.Order_Adapter_View> {

    @NonNull
    @Override
    public Order_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.orders, parent, false);
        return new Order_Adapter_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Order_Adapter_View holder, int position) {
        holder.order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.order_layout.getContext(), OrderDetailActivity.class);
                holder.order_layout.getContext().startActivity(intent );
            }
        });

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class Order_Adapter_View extends RecyclerView.ViewHolder {

        LinearLayout order_layout;
        public Order_Adapter_View(@NonNull View itemView) {
            super(itemView);
            order_layout=itemView.findViewById(R.id.order_layout);
        }
    }
}
