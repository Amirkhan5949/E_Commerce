package com.example.e_commerce_user.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.Size;

import java.util.List;

public class Size_Adapter extends RecyclerView.Adapter<Size_Adapter.Size_Adapter_View> {


    int selectedPosition = -1;

    List<Size> size;


    public Size_Adapter(List<Size> size) {
        this.size = size;
    }

    @NonNull
    @Override
    public Size_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.size,parent,false);
        return new Size_Adapter_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Size_Adapter_View holder, final int position) {

        holder.tv_size.setText(size.get(position).getTitle() );

        holder.tv_size.setBackgroundResource((selectedPosition==position ?R.drawable.bg_gray_with_stroke:R.drawable.bg_gray));

        holder.tv_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition=position;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return size.size();
    }

    class Size_Adapter_View extends RecyclerView.ViewHolder {

        TextView tv_size;
        public Size_Adapter_View(@NonNull View itemView) {
            super(itemView);

            tv_size=itemView.findViewById(R.id.tv_size);
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }
}
