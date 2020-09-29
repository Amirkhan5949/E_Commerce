package com.example.e_commerce_admin.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Category;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.ui.activity.ProductDetailActivity;
import com.example.e_commerce_admin.ui.activity.WishlistActivity;
import com.example.e_commerce_admin.utils.util;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewholder> {

    List<Product> product;
    public ProductAdapter(List<Product> product) {
        this.product=product;
    }

    @NonNull
    @Override
    public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product, parent, false);
        view.getLayoutParams().width=(int) ((util.getScreenWidth(view.getContext()) - util.dpToPx(view.getContext(),16) )/ 2);
        return new ProductViewholder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewholder holder, int position) {

        holder.p_name.setText(product.get(position).getTitle());
        holder.p_image.setImageResource(product.get(position).getImage());

        holder.p_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten =new Intent(holder.p_layout.getContext(), ProductDetailActivity.class);
                holder.p_layout.getContext().startActivity(inten);
            }
        });


    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    class ProductViewholder extends RecyclerView.ViewHolder {
        ImageView p_image;
        TextView p_name;
        LinearLayout p_layout;


        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            p_image=itemView.findViewById(R.id.p_img);
            p_name=itemView.findViewById(R.id.p_name);
            p_layout=itemView.findViewById(R.id.p_layout);

        }
    }
}
