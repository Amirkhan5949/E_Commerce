package com.example.e_commerce_admin.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;

public class ProductReview_Adapter extends RecyclerView.Adapter<ProductReview_Adapter.ProductReview_Adapter_View> {

    @NonNull
    @Override
    public ProductReview_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.productdetail_review, parent, false);
        return new ProductReview_Adapter_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductReview_Adapter_View holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ProductReview_Adapter_View  extends RecyclerView.ViewHolder {
        public ProductReview_Adapter_View(@NonNull View itemView) {
            super(itemView);
        }
    }
}
