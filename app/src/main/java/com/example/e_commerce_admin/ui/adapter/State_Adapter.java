package com.example.e_commerce_admin.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;
import com.example.e_commerce_admin.model.State;

import java.util.List;

public class State_Adapter extends RecyclerView.Adapter<State_Adapter.State_Adapter_View> {

    List<State> list;
    ClickCallBack clickCallBack;

    public State_Adapter(List<State> list,ClickCallBack clickCallBack) {
        this.list = list;
        this.clickCallBack = clickCallBack;

    }

    @NonNull
    @Override
    public State_Adapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.state,parent,false);
        return new State_Adapter_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull State_Adapter_View holder, final int position) {

        holder.tv_place.setText(list.get(position).getState());

        holder.tv_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallBack.click(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class State_Adapter_View extends RecyclerView.ViewHolder {
        TextView tv_place;
        public State_Adapter_View(@NonNull View itemView) {
            super(itemView);
            tv_place=itemView.findViewById(R.id.tv_place);
        }
    }


    public interface ClickCallBack{
        void click(int i);
    }
}
