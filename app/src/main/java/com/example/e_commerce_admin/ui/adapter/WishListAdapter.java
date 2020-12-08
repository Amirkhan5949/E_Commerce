package com.example.e_commerce_admin.ui.adapter;

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

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.model.WishList;
import com.example.e_commerce_admin.ui.activity.ProductDetailActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class WishListAdapter extends FirebaseRecyclerAdapter<Product,WishListAdapter.WishListAdapter_View> {

    public WishListAdapter(@NonNull FirebaseRecyclerOptions<Product> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final WishListAdapter_View holder, int position, @NonNull Product model) {

        final String id = getRef(position).getKey();
        holder.p_name.setText(model.getId());
        Picasso.get().load(model.getImg()).into(holder.p_image);

        Log.i("sfsfdffe", "onBindViewHolder: "+model.toString());

        holder.icon_favourite.setVisibility(View.GONE);

        holder.p_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.p_layout.getContext(), ProductDetailActivity.class);
                intent.putExtra("id", id);
                holder.p_layout.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public WishListAdapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product, parent, false);
        return new WishListAdapter_View(view);

    }

    public class WishListAdapter_View extends RecyclerView.ViewHolder {

        ImageView p_image,icon_favourite;
        TextView p_name;
        LinearLayout p_layout;

        public WishListAdapter_View(@NonNull View itemView) {
            super(itemView);

            p_image = itemView.findViewById(R.id.p_img);
            p_name = itemView.findViewById(R.id.p_name);
            p_layout = itemView.findViewById(R.id.p_layout);
            icon_favourite = itemView.findViewById(R.id.icon_favourite);

        }
    }
}
