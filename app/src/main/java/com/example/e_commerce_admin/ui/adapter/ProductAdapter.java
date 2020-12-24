package com.example.e_commerce_admin.ui.adapter;

import android.content.Context;
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
import com.example.e_commerce_admin.model.Category;
import com.example.e_commerce_admin.model.Product;
import com.example.e_commerce_admin.ui.activity.MainActivity;
import com.example.e_commerce_admin.ui.activity.ProductDetailActivity;
import com.example.e_commerce_admin.ui.activity.WishlistActivity;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.Loader;
import com.example.e_commerce_admin.utils.util;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAdapter extends FirebaseRecyclerAdapter<Product,ProductAdapter.ProductViewholder> {
private boolean heart=true;
private Loader loader;
private  Context context;
private ClickCallBack clickCallBack;

    public ProductAdapter(@NonNull FirebaseRecyclerOptions<Product> options,Context context ,ClickCallBack clickCallBack) {
        super(options);
        this.context=context;
        this.clickCallBack=clickCallBack;
        loader=new Loader(context);
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
       clickCallBack.load();
    }

    @NonNull
    @Override
    public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.product, parent, false);
        view.getLayoutParams().width=(int) ((util.getScreenWidth(view.getContext()) - util.dpToPx(view.getContext(),16) )/ 2);
        return new ProductViewholder(view);

    }


    @Override
    protected void onBindViewHolder(@NonNull final ProductViewholder holder, int position, @NonNull final Product model) {
        {
            final String id=getRef(position).getKey();

            holder.p_name.setText(model.getName());
            holder.tv_pdatail.setText(model.getDetails());
            holder.tv_mrp.setText("₹"+model.getMrp_price());
            holder.tv_sellingp.setText("₹"+model.getSelling_price());
            holder.tv_off.setText(util.discount(Integer.parseInt(model.getMrp_price())
                    ,Integer.parseInt(model.getSelling_price()))+"%"+" off");
            Picasso.get().load(model.getImg()).into(holder.p_image);

            holder.p_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent inten =new Intent(holder.p_layout.getContext(), ProductDetailActivity.class);
                   inten.putExtra("id",id);
                    holder.p_layout.getContext().startActivity(inten);
                }
            });



            if (FirebaseAuth.getInstance().getUid()!=null){
                FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.WishList.key)
                        .child(FirebaseAuth.getInstance().getUid())
                        .child(id)
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()){
                                  holder.icon_favourite.setImageResource(R.drawable.filledheart);
                                    holder.icon_favourite.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            loader.show();
                                            FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.WishList.key)
                                                    .child(FirebaseAuth.getInstance().getUid())
                                                    .child(id)
                                                    .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    loader.dismiss();
                                                    holder.icon_favourite.setImageResource(R.drawable.favorite);

                                                }
                                            });

                                        }
                                    });


                                }
                                else {
                                    holder.icon_favourite.setImageResource(R.drawable.favorite);
                                    final Map<String,Object> map = new HashMap<>();
                                    map.put(id,model);

                                    holder.icon_favourite.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.WishList.key)
                                                    .child(FirebaseAuth.getInstance().getUid())
                                                     .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                   loader.dismiss();
                                                    holder.icon_favourite.setImageResource(R.drawable.filledheart);

                                                }
                                            });
                                        }
                                    });

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

            }
            else {
                Intent intent=new Intent(holder.icon_favourite.getContext(), MainActivity.class);
                holder.icon_favourite.getContext().startActivity(intent);
            }
        }
    }


    class ProductViewholder extends RecyclerView.ViewHolder {
        ImageView p_image,icon_favourite;
        TextView p_name,tv_off,tv_mrp,tv_sellingp,tv_pdatail;
        LinearLayout p_layout;


        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            p_image=itemView.findViewById(R.id.p_img);
            p_name=itemView.findViewById(R.id.p_name);
            tv_off=itemView.findViewById(R.id.tv_off);
            tv_mrp=itemView.findViewById(R.id.tv_mrp);
            tv_sellingp=itemView.findViewById(R.id.tv_sellingp);
            p_layout=itemView.findViewById(R.id.p_layout);
            icon_favourite=itemView.findViewById(R.id.icon_favourite);
            tv_pdatail=itemView.findViewById(R.id.tv_pdatail);

        }
    }
    public interface ClickCallBack {
        void load();
    }
}
