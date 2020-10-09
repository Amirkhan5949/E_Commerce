package com.example.e_commerce_admin.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Brand;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class BrandAdapter extends FirebaseRecyclerAdapter<Brand,BrandAdapter.BrandAdapterView> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public BrandAdapter(@NonNull FirebaseRecyclerOptions<Brand> options) {
        super(options);
    }

    @NonNull
    @Override
    public BrandAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.brand, parent, false);
        return new BrandAdapterView(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull BrandAdapterView holder, int position, @NonNull Brand model) {

        Picasso.get().load(model.getImage()).into(holder.iv_brand);
        holder.tv_brandname.setText(model.getName());


    }

    class BrandAdapterView extends RecyclerView.ViewHolder {
    ImageView iv_brand;
    TextView tv_brandname,tv_product;
        public BrandAdapterView(@NonNull View itemView) {
            super(itemView);
            iv_brand=itemView.findViewById(R.id.iv_brand);
            tv_brandname=itemView.findViewById(R.id.tv_brandname);
            tv_product=itemView.findViewById(R.id.tv_productquant);
        }
    }
}
