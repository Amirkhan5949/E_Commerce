package com.example.e_commerce_admin.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.SuperCategory;
import com.example.e_commerce_admin.ui.activity.OverCatActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class HomeSuperCategoryAdapter extends FirebaseRecyclerAdapter<SuperCategory, HomeSuperCategoryAdapter.HomeSuperCategoryAdapterView> {
private ProgressBar bar;

    public HomeSuperCategoryAdapter(@NonNull FirebaseRecyclerOptions<SuperCategory> options, ProgressBar progress) {
        super(options);
        bar=progress;
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        bar.setVisibility(View.GONE);
    }

    @Override
    protected void onBindViewHolder(@NonNull HomeSuperCategoryAdapterView holder, int position, @NonNull SuperCategory model) {

        Picasso.get().load(model.getImage()).into(holder.iv_cat);
        holder.tv_supercat_name.setText(model.getName());
        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.ll_main.getContext(), OverCatActivity.class);
                intent.putExtra("id",model.getSuper_category_id());
                holder.ll_main.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public HomeSuperCategoryAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.supercategory, parent, false);
        return new HomeSuperCategoryAdapterView(view);
    }

    class HomeSuperCategoryAdapterView extends RecyclerView.ViewHolder {
        private TextView tv_supercat_name;
        private ImageView iv_cat;
        private LinearLayout ll_main;

        public HomeSuperCategoryAdapterView(@NonNull View itemView) {
            super(itemView);

            ll_main=itemView.findViewById(R.id.ll_main);
            tv_supercat_name=itemView.findViewById(R.id.tv_supercat_name);
            iv_cat=itemView.findViewById(R.id.iv_cat);
        }
    }


}
