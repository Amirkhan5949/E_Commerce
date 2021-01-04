package com.example.e_commerce_user.ui.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order_Summary_Adapter extends FirebaseRecyclerAdapter<Cart,Order_Summary_Adapter.Order_Summary_Adapter_View> {

    private List<String> list;
    private Loader loader;
    ClickCallBack clickCallBack;

    Context context;

    public Order_Summary_Adapter(@NonNull FirebaseRecyclerOptions<Cart> options, List<String> list, Context context, Order_Summary_Adapter.ClickCallBack clickCallBack) {
        super(options);
        this.context=context;
        this.clickCallBack=clickCallBack;
        loader=new Loader(context);
        this.list=list;
    }

    @NonNull
    @Override
    public Order_Summary_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.order_summary, parent, false);
        return new Order_Summary_Adapter_View(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final Order_Summary_Adapter_View holder, int position, @NonNull final Cart model) {

        final String id=getRef(position).getKey();

        holder.iv_expandqty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialog = DialogPlus.newDialog(holder.iv_expandqty.getContext())
                        .setContentHolder(new ViewHolder(R.layout.sizelist))
                        .setCancelable(true)
                        .setGravity(Gravity.CENTER)
                        .setMargin(util.dpToPx(holder.iv_expandqty.getContext(),70),util.dpToPx(holder.iv_expandqty.getContext(),150),util.dpToPx(holder.iv_expandqty.getContext(),70),util.dpToPx(holder.iv_expandqty.getContext(),150))
                        .create();

                View viewholder = dialog.getHolderView();

                RecyclerView rv_sizelist=viewholder.findViewById(R.id.rv_sizelist);
                rv_sizelist.setLayoutManager(new LinearLayoutManager(holder.iv_expandqty.getContext(),LinearLayoutManager.VERTICAL,false));
                rv_sizelist.setAdapter(new SizeListAdapter(list,new SizeListAdapter.ClickCallBack() {
                    @Override
                    public void click(int i) {

                        String selling_p=(((Integer.parseInt(list.get(i)))*(Integer.parseInt(model.getProduct().getSelling_price()+"")))+"");
                        clickCallBack.click(list.get(i),model);

                        Map<String,Object> map=new HashMap<>();
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


        Picasso.get().load(model.getProduct().getImg()).into(holder.iv_image);
        holder.tv_name.setText(model.getProduct().getName());
        holder.tv_description.setText(model.getProduct().getDetails());
        holder.tv_quantity.setText(model.getQuantity()+"");
        holder.tv_size.setText(model.getSize().getTitle()+"");
        holder.tv_sellingp.setText("₹"+Integer.parseInt(model.getProduct().getSelling_price())*(model.getQuantity())+"");
        holder.tv_order_rs.setText("₹"+Integer.parseInt(model.getProduct().getSelling_price())*(model.getQuantity())+"");
        holder.tv_mrp.setText("₹"+Integer.parseInt(model.getProduct().getMrp_price())*(model.getQuantity())+"");

        holder.colorview.setBackgroundColor(android.graphics.Color.parseColor(model.getColor().getColor()+""));

    }

    class Order_Summary_Adapter_View extends RecyclerView.ViewHolder {
        private TextView tv_orderid,tv_order_rs,tv_date,tv_name,tv_description,tv_size,
                tv_quantity,tv_sellingp,tv_mrp,tv_off;
        private View colorview;
        ImageView iv_expandqty,iv_image;
        public Order_Summary_Adapter_View(@NonNull View itemView) {
            super(itemView);
            tv_orderid=itemView.findViewById(R.id.tv_orderid);
            tv_order_rs=itemView.findViewById(R.id.tv_order_rs);
            tv_date=itemView.findViewById(R.id.tv_date);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_description=itemView.findViewById(R.id.tv_description);
            tv_size=itemView.findViewById(R.id.tv_size);
            tv_quantity=itemView.findViewById(R.id.tv_quantity);
            tv_sellingp=itemView.findViewById(R.id.tv_sellingp);
            tv_mrp=itemView.findViewById(R.id.tv_mrp);
            tv_off=itemView.findViewById(R.id.tv_off);
            colorview=itemView.findViewById(R.id.colorview);
            iv_expandqty=itemView.findViewById(R.id.iv_expandqty);
            iv_image=itemView.findViewById(R.id.iv_image);

        }
    }

    public interface ClickCallBack{

        void click( String newQty, Cart model);
    }
}
