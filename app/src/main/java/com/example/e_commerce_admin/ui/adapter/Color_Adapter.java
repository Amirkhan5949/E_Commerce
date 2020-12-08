package com.example.e_commerce_admin.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.Color;

import java.util.List;

public class Color_Adapter extends RecyclerView.Adapter<Color_Adapter.Color_Adapter_View> {

    List<Color> list;
    int selectedPosition = -1;

    public Color_Adapter(List<Color> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Color_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.color,parent,false);
        return new Color_Adapter_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Color_Adapter_View holder, final int position) {

        holder.iv_color.setImageResource(list.get(position).getColor().equals("#FFFFFF")?R.drawable.right_black:R.drawable.right_white);

        holder.iv_color.setVisibility(selectedPosition == position?View.VISIBLE:View.GONE);
        holder.ll_color.setBackgroundColor(android.graphics.Color.parseColor(list.get(position).getColor()));

        if (position==2){
            holder.iv_color.setImageResource(R.drawable.right_black);
        }
        else {
            holder.iv_color.setImageResource(R.drawable.right_white);
        }


        holder.ll_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Color_Adapter_View extends RecyclerView.ViewHolder {
        ImageView iv_color;
        LinearLayout ll_color;
     public Color_Adapter_View(@NonNull View itemView) {
         super(itemView);
         iv_color=itemView.findViewById(R.id.iv_color);
         ll_color=itemView.findViewById(R.id.ll_color);
     }
 }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
