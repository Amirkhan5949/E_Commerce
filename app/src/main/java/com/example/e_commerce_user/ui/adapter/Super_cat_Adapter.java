package com.example.e_commerce_user.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.SuperCategory;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Super_cat_Adapter extends FirebaseRecyclerAdapter<SuperCategory,Super_cat_Adapter.Super_cat_Viewholder> {
    Context context;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Super_cat_Adapter(@NonNull FirebaseRecyclerOptions<SuperCategory> options) {
        super(options);
    }


    @NonNull
    @Override
    public Super_cat_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.super_cat, parent, false);

        return new Super_cat_Viewholder(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull Super_cat_Viewholder holder, int position, @NonNull SuperCategory model) {

        holder.tv_supercat_name.setText(model.getName());
    }


    class Super_cat_Viewholder extends RecyclerView.ViewHolder {

        TextView tv_supercat_name;
        public Super_cat_Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_supercat_name=itemView.findViewById(R.id.tv_supercat_name);
        }
    }
}
