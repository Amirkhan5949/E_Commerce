package com.example.e_commerce_admin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Category;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.utils.util;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewholder> {
    Context context;
    List<Product> product;
    public ProductAdapter(List<Product> product, Context context) {
        this.product=product;
        this.context=context;
    }

    @NonNull
    @Override
    public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product, parent, false);
        view.getLayoutParams().width=(int) ((util.getScreenWidth(context) - util.dpToPx(context,16) )/ 2);
        return new ProductViewholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewholder holder, int position) {

        holder.p_name.setText(product.get(position).getTitle());
        holder.p_image.setImageResource(product.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    class ProductViewholder extends RecyclerView.ViewHolder {
        ImageView p_image;
        TextView p_name;

        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            p_image=itemView.findViewById(R.id.p_img);
            p_name=itemView.findViewById(R.id.p_name);
        }
    }
}
