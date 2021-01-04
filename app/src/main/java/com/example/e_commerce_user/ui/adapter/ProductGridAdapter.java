package com.example.e_commerce_user.ui.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Product;
import com.example.e_commerce_user.ui.activity.ProductDetailActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class ProductGridAdapter extends FirebaseRecyclerAdapter<Product, ProductGridAdapter.ProductViewholder> {


    public ProductGridAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @NonNull
    @Override
    public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product, parent, false);
        return new ProductViewholder(view);


    }


    @Override
    protected void onBindViewHolder(@NonNull final ProductViewholder holder, int position, @NonNull Product model) {
        final String id = getRef(position).getKey();
        holder.p_name.setText(model.getName());
        Picasso.get().load(model.getImg()).into(holder.p_image);

        Log.i("gfgfgf", "onBindViewHolder: "+model.toString());

        holder.p_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.p_layout.getContext(), ProductDetailActivity.class);
                intent.putExtra("id", id);
                holder.p_layout.getContext().startActivity(intent);
            }
        });

    }

    class ProductViewholder extends RecyclerView.ViewHolder {
        ImageView p_image;
        TextView p_name;
        LinearLayout p_layout;

        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            p_image = itemView.findViewById(R.id.p_img);
            p_name = itemView.findViewById(R.id.p_name);
            p_layout = itemView.findViewById(R.id.p_layout);
        }
    }
}
