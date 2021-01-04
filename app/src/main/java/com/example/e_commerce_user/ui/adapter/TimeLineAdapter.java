package com.example.e_commerce_user.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_commerce_user.R;
import com.example.e_commerce_user.model.TimeLineModel;
import com.github.vipulasri.timelineview.TimelineView;

import java.util.List;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineAdapter.TimeLineViewHolder> {

     private List<TimeLineModel> list;
    private boolean shipingStatus;

    public TimeLineAdapter(List<TimeLineModel> list  ) {
        this.list = list;
      }

    @NonNull
    @Override
    public TimeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.track_order, parent, false);
        return new TimeLineViewHolder(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineViewHolder holder, int position) {

        if(list.get(position).getStatus()==1){
            holder.timelineView.setMarker(holder.timelineView.getContext().getResources().getDrawable(R.drawable.dot_green));
            holder.textView.setTextColor(holder.detail.getContext().getResources().getColor(R.color.black));
        }
        else if(list.get(position).getStatus()==0){
            holder.timelineView.setMarker(holder.timelineView.getContext().getResources().getDrawable(R.drawable.dot_gray));
            holder.textView.setTextColor(holder.detail.getContext().getResources().getColor(R.color.gray));
        }
        else if(list.get(position).getStatus()==-1){
            holder.timelineView.setMarker(holder.timelineView.getContext().getResources().getDrawable(R.drawable.dot_primery));
            holder.textView.setTextColor(holder.detail.getContext().getResources().getColor(R.color.red));
        }

        //holder.detail.setTextColor(holder.detail.getContext().getResources().getColor(R.color.gray));
        holder.textView.setText(list.get(position).getTitle());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TimeLineViewHolder extends RecyclerView.ViewHolder{

        TimelineView timelineView;
        TextView textView,time,detail;
        RecyclerView rv_ShipingDetail;


        public TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            timelineView = (TimelineView) itemView.findViewById(R.id.timeline);
            textView =  itemView.findViewById(R.id.textView);
            time =  itemView.findViewById(R.id.time);
            detail =  itemView.findViewById(R.id.detail);
            rv_ShipingDetail =  itemView.findViewById(R.id.rv_ShipingDetail);
            rv_ShipingDetail.setLayoutManager(new LinearLayoutManager(rv_ShipingDetail.getContext()));
            timelineView.initLine(viewType);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }
}
