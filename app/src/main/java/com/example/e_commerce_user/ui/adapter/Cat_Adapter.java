package com.example.e_commerce_user.ui.adapter;

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

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.SuperCategory;
import com.example.e_commerce_user.ui.activity.ProductListActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class Cat_Adapter extends FirebaseRecyclerAdapter<SuperCategory,Cat_Adapter.Cat_Adapter_View> {

  private ClickCallBack clickCallBack;
   private ProgressBar bar;


    public Cat_Adapter(@NonNull FirebaseRecyclerOptions<SuperCategory> options, ProgressBar progress, ClickCallBack clickCallBack) {
        super(options);
        this.clickCallBack = clickCallBack;
        bar=progress;
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
         clickCallBack.load(getItemCount());

        bar.setVisibility(View.GONE);

    }

    @NonNull
    @Override
    public Cat_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.categoryrecycler, parent, false);
        return new Cat_Adapter_View(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull Cat_Adapter_View holder, final int position, @NonNull SuperCategory model) {
        final String id=getRef(position).getKey();

        Picasso.get().load(model.getImage()).into(holder.iv_cat);
        holder.tv_supercat_name.setText(model.getName());

        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallBack.click(position,id);
                Intent  intent=new Intent(holder.ll_main.getContext(), ProductListActivity.class);
                intent.putExtra("supercatid",getRef(position).getKey());
                holder.ll_main.getContext().startActivity(intent);
             }
        });

    }


    class Cat_Adapter_View extends RecyclerView.ViewHolder {
        TextView tv_supercat_name;
        ImageView iv_cat;
        LinearLayout ll_main;

        public Cat_Adapter_View(@NonNull View itemView) {
            super(itemView);
            ll_main=itemView.findViewById(R.id.ll_main);
            tv_supercat_name=itemView.findViewById(R.id.tv_supercat_name);
            iv_cat=itemView.findViewById(R.id.iv_cat);
        }
    }

    public interface ClickCallBack{
        void click(int i,String id );
        void load(int count);
      }


}
