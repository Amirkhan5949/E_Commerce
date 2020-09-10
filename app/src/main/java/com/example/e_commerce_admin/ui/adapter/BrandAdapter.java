package com.example.e_commerce_admin.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.BrandAdapterView> {

    @NonNull
    @Override
    public BrandAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.brand, parent, false);
        return new BrandAdapterView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandAdapterView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class BrandAdapterView extends RecyclerView.ViewHolder {
        public BrandAdapterView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
