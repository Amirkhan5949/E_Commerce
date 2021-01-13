package com.example.e_commerce_user.ui.adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Brand;
import com.example.e_commerce_user.ui.activity.BrandActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class BrandAdapter extends FirebaseRecyclerAdapter<Brand,BrandAdapter.BrandAdapterView> {

    private ClickCallBack clickCallBack;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BrandAdapter(@NonNull FirebaseRecyclerOptions<Brand> options,ClickCallBack  clickCallBack) {
        super(options);
       this.clickCallBack=clickCallBack;
    }

    @NonNull
    @Override
    public BrandAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.brand, parent, false);
        return new BrandAdapterView(view);
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        clickCallBack.load();
    }

    @Override
    protected void onBindViewHolder(@NonNull BrandAdapterView holder, int position, @NonNull Brand model) {

        Picasso.get().load(model.getImage()).into(holder.iv_brand);
        holder.tv_brandname.setText(model.getName());

        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.ll_main.getContext(), BrandActivity.class);
                intent.putExtra("brandid",position);
                holder.ll_main.getContext().startActivity(intent);
            }
        });

    }

    class BrandAdapterView extends RecyclerView.ViewHolder {
   private CircleImageView iv_brand;
    private TextView tv_brandname;
    private LinearLayout ll_main;

        public BrandAdapterView(@NonNull View itemView) {
            super(itemView);
            iv_brand=itemView.findViewById(R.id.iv_brand);
            tv_brandname=itemView.findViewById(R.id.tv_brandname);
            ll_main=itemView.findViewById(R.id.ll_main);

        }
    }
    public interface ClickCallBack{
        void load();
    }
}
