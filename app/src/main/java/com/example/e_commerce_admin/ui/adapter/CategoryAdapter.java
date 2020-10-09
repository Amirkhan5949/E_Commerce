package com.example.e_commerce_admin.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.utils.Utils;
import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Category;
import com.example.e_commerce_admin.model.SuperCategory;
import com.example.e_commerce_admin.utils.util;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends FirebaseRecyclerAdapter<SuperCategory,CategoryAdapter.CategoryViewholder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param opt
     */
    public CategoryAdapter(@NonNull FirebaseRecyclerOptions<SuperCategory> opt) {
        super(opt);
    }

    @NonNull
    @Override
    public CategoryViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category, parent, false);
        view.getLayoutParams().width = (int) ((util.getScreenWidth(view.getContext()) - util.dpToPx(view.getContext(),16) )/ 4); /// THIS LINE WILL DIVIDE OUR VIEW INTO NUMBERS OF PARTS
        return new CategoryViewholder(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull CategoryViewholder holder, int position, @NonNull SuperCategory model) {
            holder.tv_Main.setText(model.getName());
        Picasso.get().load(model.getImage()).into(holder.iv_Main);
        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

     }


    class CategoryViewholder extends RecyclerView.ViewHolder {

        TextView tv_Main;
        ImageView iv_Main;
        LinearLayout ll_main;

        public CategoryViewholder(@NonNull View itemView) {
            super(itemView);

            ll_main=itemView.findViewById(R.id.ll_main);
            tv_Main = itemView.findViewById(R.id.tv_Main);
            iv_Main = itemView.findViewById(R.id.iv_Main);
        }
    }
}
