package com.example.phoebee.superiorproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.phoebee.superiorproject.model.History;
import com.example.phoebee.superiorproject.model.ItemClickListener;


import java.util.ArrayList;
import java.util.List;

class HistoryItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    private ItemClickListener itemClickListener;
    public HistoryItemViewHolder(View itemView){
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;
    }
}

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistorysHolder>{
    Context mContext;
    List<History> mHistory;

    public HistoryAdapter(Context context, List<History> history){
        this.mContext = context;
        this.mHistory = history;
    }

    @Override
    public HistoryAdapter.HistorysHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.record_item, parent, shouldAttachToParentImmediately);
        HistorysHolder viewHolder = new HistorysHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.HistorysHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mHistory.size();
    }

    public void setHistory(List<History> history){
        this.mHistory= history;
        notifyDataSetChanged();
    }

    class HistorysHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;

        public HistorysHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            title.setText("Title: " + mHistory.get(listIndex).getTitle());

        }

        @Override
        public void onClick(View v) {
            Context context = v.getContext();
            Intent intent = new Intent(context, History_list_item.class);
            intent.putExtra("num",getAdapterPosition());
            context.startActivity(intent);
        }
    }

}