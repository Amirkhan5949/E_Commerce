package com.example.e_commerce_admin.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.SuperCategory;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class Cat_Adapter extends FirebaseRecyclerAdapter<SuperCategory,Cat_Adapter.Cat_Adapter_View> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Cat_Adapter(@NonNull FirebaseRecyclerOptions<SuperCategory> options) {
        super(options);
    }

    @NonNull
    @Override
    public Cat_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.categoryrecycler, parent, false);
        return new Cat_Adapter_View(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull Cat_Adapter_View holder, int position, @NonNull SuperCategory model) {
        Picasso.get().load(model.getImage()).into(holder.iv_cat);
        holder.tv_supercat_name.setText(model.getName());

    }


    class Cat_Adapter_View extends RecyclerView.ViewHolder {
        TextView tv_supercat_name;
        ImageView iv_cat;

        public Cat_Adapter_View(@NonNull View itemView) {
            super(itemView);
            tv_supercat_name=itemView.findViewById(R.id.tv_supercat_name);
            iv_cat=itemView.findViewById(R.id.iv_cat);
        }
    }
}
