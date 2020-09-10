package com.example.e_commerce_admin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.utils.Utils;
import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Category;
import com.example.e_commerce_admin.utils.util;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewholder> {

    List<Category> category;
    Context context;

    public CategoryAdapter(List<Category> category, Context context) {
        this.category = category;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category, parent, false);
        view.getLayoutParams().width = (int) ((util.getScreenWidth(context) - util.dpToPx(context,16) )/ 4); /// THIS LINE WILL DIVIDE OUR VIEW INTO NUMBERS OF PARTS
        return new CategoryViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewholder holder, int position) {
        holder.tv_Main.setText(category.get(position).getTitle());
        holder.iv_Main.setImageResource(category.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return category.size();
    }

    class CategoryViewholder extends RecyclerView.ViewHolder {

        TextView tv_Main;
        ImageView iv_Main;

        public CategoryViewholder(@NonNull View itemView) {
            super(itemView);

            tv_Main = itemView.findViewById(R.id.tv_Main);
            iv_Main = itemView.findViewById(R.id.iv_Main);
        }
    }
}
