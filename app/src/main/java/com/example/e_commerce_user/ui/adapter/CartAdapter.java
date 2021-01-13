package com.example.e_commerce_user.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Cart;
import com.example.e_commerce_user.utils.FirebaseConstants;
import com.example.e_commerce_user.utils.Loader;
import com.example.e_commerce_user.utils.util;
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
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartAdapter extends FirebaseRecyclerAdapter<Cart,CartAdapter.ReviewAdapterView> {

    private List<String>list;
   private Loader loader;
   private ClickCallBack clickCallBack;


   private Context context;

    public CartAdapter(@NonNull FirebaseRecyclerOptions<Cart> options, List<String> list, Context context, ClickCallBack clickCallBack) {
        super(options);
        this.context=context;
        this.clickCallBack=clickCallBack;
        loader=new Loader(context);
        this.list=list;
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();

        clickCallBack.load(getItemCount());
    }

    @NonNull
    @Override
    public ReviewAdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.review, parent, false);
        return new ReviewAdapterView(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull final ReviewAdapterView holder, final int position, @NonNull final Cart model) {

        final String id=getRef(position).getKey();

        holder.ll_extend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialog = DialogPlus.newDialog(holder.ll_extend.getContext())
                        .setContentHolder(new ViewHolder(R.layout.sizelist))
                        .setCancelable(true)
                        .setGravity(Gravity.CENTER)
                        .setMargin(util.dpToPx(holder.ll_extend.getContext(),70),util.dpToPx(holder.ll_extend.getContext(),150),util.dpToPx(holder.ll_extend.getContext(),70),util.dpToPx(holder.ll_extend.getContext(),150))
                        .create();

                View viewholder = dialog.getHolderView();


                RecyclerView rv_sizelist=viewholder.findViewById(R.id.rv_sizelist);
                rv_sizelist.setLayoutManager(new LinearLayoutManager(holder.ll_extend.getContext(),LinearLayoutManager.VERTICAL,false));
                rv_sizelist.setAdapter(new SizeListAdapter(list,new SizeListAdapter.ClickCallBack() {
                    @Override
                    public void click(int i) {

                        String selling_p=(((Integer.parseInt(list.get(i)))*(Integer.parseInt(model.getProduct().getSelling_price()+"")))+"");
                        clickCallBack.click(list.get(i),model);

                        Map<String,Object>map=new HashMap<>();
                        map.put(FirebaseConstants.Cart.quantity,Integer.parseInt(list.get(i)));

                        loader.show();
                        FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.Cart.key)
                                .child(FirebaseAuth.getInstance().getUid())
                                .child(id)
                                .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                loader.dismiss();
                            }
                        });


                        dialog.dismiss();
                    }
                }));

                dialog.show();

            }
        });

        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.WishList.key)
                .child(FirebaseAuth.getInstance().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists()){
                            if (snapshot.hasChild(getRef(position).getKey())) {
                                holder.r_wishlist.setText("Remove from wishlist");
                            }
                            else {
                                holder.r_wishlist.setText("Add to wishlist");
                            }
                        }


                        holder.r_wishlist.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (holder.r_wishlist.getText().equals("Add to wishlist")) {

                                    loader.show();
                                    Map<String,Object> map = new HashMap<>();
                                    map.put(id,model.getProduct());

                                    FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.WishList.key)
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            loader.dismiss();
                                            holder.r_wishlist.setText("Remove from wishlist");

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            loader.dismiss();
                                        }
                                    });


                                }
                                else {

                                    loader.show();
                                    FirebaseDatabase.getInstance().getReference()
                                            .child(FirebaseConstants.WishList.key)
                                            .child(FirebaseAuth.getInstance().getUid())
                                            .child(id)
                                            .removeValue()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    loader.dismiss();
                                                    holder.r_wishlist.setText("Add to wishlist");

                                                }
                                            });

                                }
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });





        holder.tv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clickCallBack.remove(model,id);

            }
        });


        Picasso.get().load(model.getProduct().getImg()).into(holder.iv_image);
        holder.tv_name.setText(model.getProduct().getName());
        holder.tv_description.setText(model.getProduct().getDetails());
        holder.tv_quantity.setText(model.getQuantity()+"");
        holder.tv_size.setText(model.getSize().getTitle()+"");
        holder.tv_sellingp.setText("₹"+Integer.parseInt(model.getProduct().getSelling_price())+"");
        holder.tv_cost.setText("₹"+Integer.parseInt(model.getProduct().getMrp_price())+"");

        holder.colorview.setBackgroundColor(android.graphics.Color.parseColor(model.getColor().getColor()+""));
    }


    class ReviewAdapterView  extends RecyclerView.ViewHolder {
        ImageView iv_image;
        TextView tv_name,tv_description,tv_size,tv_quantity,tv_sellingp,tv_offer,tv_cost,r_wishlist,tv_remove;
        View colorview;
        LinearLayout ll_extend;
        public ReviewAdapterView(@NonNull View itemView) {
            super(itemView);
            iv_image=itemView.findViewById(R.id.iv_image);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_description=itemView.findViewById(R.id.tv_description);
            tv_quantity=itemView.findViewById(R.id.tv_quantity);
            tv_size=itemView.findViewById(R.id.tv_size);
            tv_sellingp=itemView.findViewById(R.id.tv_sellingp);
            tv_offer=itemView.findViewById(R.id.tv_offer);
            tv_cost=itemView.findViewById(R.id.tv_cost);
            r_wishlist=itemView.findViewById(R.id.r_wishlist);
            tv_remove=itemView.findViewById(R.id.tv_remove);
            colorview=itemView.findViewById(R.id.colorview);
            ll_extend=itemView.findViewById(R.id.ll_extend);

        }
    }
    public interface ClickCallBack{

        void click( String newQty, Cart model);

        void remove( Cart model,String id);



        void load(int i);
    }
}
