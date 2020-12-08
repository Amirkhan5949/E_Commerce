package com.example.e_commerce_admin.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_admin.R;

import java.util.ArrayList;
import java.util.List;

public class SizeListAdapter extends RecyclerView.Adapter<SizeListAdapter.SizeListAdapter_View> {
    private List<String> list;
    private ClickCallBack clickCallBack;


    public SizeListAdapter(List<String> list,ClickCallBack clickCallBack) {
        this.list=list;
        this.clickCallBack=clickCallBack;
    }

    @NonNull
    @Override
    public SizeListAdapter_View onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.listofsize, parent, false);
        return new SizeListAdapter_View(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeListAdapter_View holder, final int position) {

        holder.tv_no.setText(list.get(position) );
        holder.tv_no.setOnClickListener(new View.OnClickListener() {
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

    public class SizeListAdapter_View extends RecyclerView.ViewHolder {
        TextView tv_no;
        public SizeListAdapter_View(@NonNull View itemView) {
            super(itemView);

            tv_no=itemView.findViewById(R.id.tv_no);
        }
    }

    public interface ClickCallBack{
        void click(int i);
    }
}
