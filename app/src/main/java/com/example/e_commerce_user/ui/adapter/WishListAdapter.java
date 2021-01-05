package com.example.e_commerce_user.ui.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.utils.Utils;
import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Product;
import com.example.e_commerce_user.ui.activity.ProductDetailActivity;
import com.example.e_commerce_user.utils.util;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class WishListAdapter extends FirebaseRecyclerAdapter<Product,WishListAdapter.WishListAdapter_View> {

   private ProgressBar  progressBar;
    public WishListAdapter(@NonNull FirebaseRecyclerOptions<Product> options, ProgressBar progress) {
        super(options);
         progressBar=progress;
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onBindViewHolder(@NonNull final WishListAdapter_View holder, int position, @NonNull Product model) {

        final String id = getRef(position).getKey();
        holder.p_name.setText(model.getName());
        holder.tv_mrp.setText(model.getMrp_price());
        holder.tv_pdatail.setText(model.getDetails());
        holder.tv_sellingp.setText(model.getSelling_price());

        holder.tv_off.setText(util.discount(Integer.parseInt(model.getMrp_price())
                ,Integer.parseInt(model.getSelling_price()))+"%"+" off");
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
        TextView p_name,tv_sellingp,tv_mrp,tv_off,tv_pdatail;
        LinearLayout p_layout;

        public WishListAdapter_View(@NonNull View itemView) {
            super(itemView);

            p_image = itemView.findViewById(R.id.p_img);
            p_name = itemView.findViewById(R.id.p_name);
            p_layout = itemView.findViewById(R.id.p_layout);
            icon_favourite = itemView.findViewById(R.id.icon_favourite);
            tv_off = itemView.findViewById(R.id.tv_off);
            tv_sellingp = itemView.findViewById(R.id.tv_sellingp);
            tv_mrp = itemView.findViewById(R.id.tv_mrp);
            tv_pdatail = itemView.findViewById(R.id.tv_pdatail);

        }
    }
}
