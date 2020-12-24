package com.example.e_commerce_admin.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Order;
import com.example.e_commerce_admin.ui.activity.OrderDetailActivity;
import com.example.e_commerce_admin.utils.FirebaseConstants;
import com.example.e_commerce_admin.utils.Loader;
import com.example.e_commerce_admin.utils.util;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order_Adapter extends FirebaseRecyclerAdapter<Order, Order_Adapter.Order_Adapter_View> {

   private Loader loader;
  private  ProgressBar progressBar ;

    public Order_Adapter(@NonNull FirebaseRecyclerOptions<Order> options, Context context, ProgressBar progress) {
        super(options);
        progressBar=progress;
        this.loader = new Loader(context);
    }

    @NonNull
    @Override
    public Order_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.orders, parent, false);
        return new Order_Adapter_View(view);
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    protected void onBindViewHolder(@NonNull final Order_Adapter_View holder, final int position, @NonNull final Order model) {

        holder.tv_cod.setText(model.getPayment_type());
        holder.tv_order_rs.setText("₹"+model.getProduct().getSelling_price());
        holder.tv_name.setText(model.getProduct().getName());
        holder.tv_description.setText(model.getProduct().getDetails());
        holder.tv_size.setText(model.getProduct().getSize().toString());
        holder.tv_sellingp.setText("₹"+model.getProduct().getSelling_price());
        holder.tv_mrp.setText("₹"+model.getProduct().getMrp_price());
        holder.tv_quantity.setText(model.getQuantity() + "");
        holder.tv_orderid.setText(getRef(position).getKey()+"");

        holder.tv_off.setText(util.discount(Integer.parseInt(model.getOrdered_mrp_price())
                ,Integer.parseInt(model.getOrdered_selling_price()))+"%"+" off");
         Log.i("fsffsds", "onBindViewHolder: "+model.getQuantity());


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        String dateString = formatter.format(new Date(model.getTime()));
        holder.tv_time.setText("Date : " + dateString);






        holder.tv_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final DialogPlus dialog = DialogPlus.newDialog(holder.tv_status.getContext())
                        .setContentHolder(new ViewHolder(R.layout.status))
                        .setCancelable(false)
                        .setMargin(util.dpToPx(holder.tv_cancel.getContext(), 20)
                                , util.dpToPx(holder.tv_cancel.getContext(), 200)
                                , util.dpToPx(holder.tv_cancel.getContext(), 20)
                                , util.dpToPx(holder.tv_cancel.getContext(), 200))
                        .setGravity(Gravity.CENTER)
                        .create();
                dialog.show();

                View viewholder = dialog.getHolderView();
                TextView tv_confirm=viewholder.findViewById(R.id.tv_confirm);
                TextView tv_Packed=viewholder.findViewById(R.id.tv_Packed);
                TextView tv_Shipped=viewholder.findViewById(R.id.tv_Shipped);
                TextView tv_Delivered=viewholder.findViewById(R.id.tv_Delivered);



                tv_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         Map<String, Object> map = new HashMap<>();
                         map.put(FirebaseConstants.Order.order_status,"Confirm");

                         FirebaseDatabase.getInstance().getReference()
                                 .child(FirebaseConstants.Order.key)
                                 .child(getRef(position).getKey())
                                 .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                 loader.dismiss();
                                 dialog.dismiss();
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 loader.dismiss();
                             }
                         });
                     }
                });
                tv_Packed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loader.show();
                         Map<String, Object> map = new HashMap<>();
                         map.put(FirebaseConstants.Order.order_status,"Packed");

                         FirebaseDatabase.getInstance().getReference()
                                 .child(FirebaseConstants.Order.key)
                                 .child(getRef(position).getKey())
                                 .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                 loader.dismiss();
                                 dialog.dismiss();
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 dialog.dismiss();
                             }
                         });

                    }
                });
                tv_Shipped.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loader.show();
                         Map<String, Object> map = new HashMap<>();
                         map.put(FirebaseConstants.Order.order_status,"Shipped");

                         FirebaseDatabase.getInstance().getReference()
                                 .child(FirebaseConstants.Order.key)
                                 .child(getRef(position).getKey())
                                 .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                 dialog.dismiss();
                                 loader.dismiss();

                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 loader.dismiss();
                             }
                         });
                    }
                });
                tv_Delivered.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loader.show();
                         Map<String, Object> map = new HashMap<>();
                         map.put(FirebaseConstants.Order.order_status,"Delivered");

                         FirebaseDatabase.getInstance().getReference()
                                 .child(FirebaseConstants.Order.key)
                                 .child(getRef(position).getKey())
                                 .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                 loader.dismiss();
                                 dialog.dismiss();
                             }
                         }).addOnFailureListener(new OnFailureListener() {
                             @Override
                             public void onFailure(@NonNull Exception e) {
                                 dialog.dismiss();
                             }
                         });

                    }
                });





            }
        });

        FirebaseDatabase.getInstance().getReference().child(FirebaseConstants.Order.key)
                .child(getRef(position).getKey())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String a=snapshot.child(FirebaseConstants.Order.order_status).getValue(String.class);
                        holder.tv_status.setText(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        Picasso.get().load(model.getProduct().getImg()).into(holder.iv_image);


        holder.order_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.order_layout.getContext(), OrderDetailActivity.class);
                intent.putExtra("order_id", getRef(position).getKey());
                holder.order_layout.getContext().startActivity(intent);
            }
        });


        holder.tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialog = DialogPlus.newDialog(holder.tv_cancel.getContext())
                        .setContentHolder(new ViewHolder(R.layout.confirmation))
                        .setCancelable(false)
                        .setMargin(util.dpToPx(holder.tv_cancel.getContext(), 20)
                                , util.dpToPx(holder.tv_cancel.getContext(), 200)
                                , util.dpToPx(holder.tv_cancel.getContext(), 20)
                                , util.dpToPx(holder.tv_cancel.getContext(), 200))
                        .setGravity(Gravity.CENTER)
                        .create();
                dialog.show();

                View viewholder = dialog.getHolderView();
                Button btn_yes = viewholder.findViewById(R.id.btn_yes);
                Button btn_no = viewholder.findViewById(R.id.btn_no);

                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loader.show();
                        Map<String, Object> map = new HashMap<>();
                        map.put(FirebaseConstants.Order.order_status, "Cancel");
                        FirebaseDatabase.getInstance().getReference()
                                .child(FirebaseConstants.Order.key)
                                .child(getRef(position).getKey())
                                .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                loader.dismiss();
                                dialog.dismiss();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                loader.dismiss();
                                Log.i("cxxv", "onFailure: " + e.getMessage());
                            }
                        });

                    }
                });

            }
        });
    }

    class Order_Adapter_View extends RecyclerView.ViewHolder {

        LinearLayout order_layout;

        TextView tv_cod, tv_order_rs, tv_name, tv_description, tv_size,tv_orderid,
                tv_quantity,tv_status, tv_off,tv_sellingp, tv_mrp, tv_help, tv_cancel, tv_time;
        View colorview;
        ImageView iv_expandqty, iv_image;

        public Order_Adapter_View(@NonNull View itemView) {
            super(itemView);
            order_layout = itemView.findViewById(R.id.order_layout);
            tv_cod = itemView.findViewById(R.id.tv_cod);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_off = itemView.findViewById(R.id.tv_off);
            tv_orderid = itemView.findViewById(R.id.tv_orderid);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_order_rs = itemView.findViewById(R.id.tv_order_rs);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_size = itemView.findViewById(R.id.tv_size);
            tv_quantity = itemView.findViewById(R.id.tv_quantity);
            tv_sellingp = itemView.findViewById(R.id.tv_sellingp);
            tv_mrp = itemView.findViewById(R.id.tv_mrp);
            tv_help = itemView.findViewById(R.id.tv_help);
            tv_cancel = itemView.findViewById(R.id.tv_cancel);
            colorview = itemView.findViewById(R.id.colorview);
            iv_expandqty = itemView.findViewById(R.id.iv_expandqty);
            iv_image = itemView.findViewById(R.id.iv_image);
        }
    }
}
