package com.example.e_commerce_admin.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewAdapterView> {

    @NonNull
    @Override
    public ReviewAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.review, parent, false);
        return new ReviewAdapterView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapterView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ReviewAdapterView  extends RecyclerView.ViewHolder {
        public ReviewAdapterView(@NonNull View itemView) {
            super(itemView);
        }
    }
}
