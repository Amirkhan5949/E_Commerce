package com.example.e_commerce_admin.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;

public class Cat_Adapter extends RecyclerView.Adapter<Cat_Adapter.Cat_Adapter_View> {
    @NonNull
    @Override
    public Cat_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.categoryrecycler, parent, false);
        return new Cat_Adapter_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Cat_Adapter_View holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    class Cat_Adapter_View extends RecyclerView.ViewHolder {
        public Cat_Adapter_View(@NonNull View itemView) {
            super(itemView);
        }
    }
}
