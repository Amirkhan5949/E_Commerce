package com.example.e_commerce_admin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.utils.util;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewholder> {
    Context context;
    public ProductAdapter( Context context) {
        this.context=context;
    }

    @NonNull
    @Override
    public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product, parent, false);
        view.getLayoutParams().width=(int) ((util.getScreenWidth(context) - util.dpToPx(context,20) )/ 2);
        return new ProductViewholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ProductViewholder extends RecyclerView.ViewHolder {
        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
