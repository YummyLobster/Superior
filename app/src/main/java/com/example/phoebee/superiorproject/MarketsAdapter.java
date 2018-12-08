package com.example.phoebee.superiorproject;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.phoebee.superiorproject.model.ItemClickListener;
import com.example.phoebee.superiorproject.model.Markets;

import java.util.ArrayList;

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private ItemClickListener itemClickListener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition(), true);
        return true;
    }
}

public class MarketsAdapter extends RecyclerView.Adapter<MarketsAdapter.NewsHolder> {
    Fragment mFragment;
    ArrayList<Markets> mMarkets;

    public MarketsAdapter(SearchFragment fragment, ArrayList<Markets> markets) {
        this.mFragment = fragment;
        this.mMarkets = markets;
    }

    @Override
    public MarketsAdapter.NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.list_item, parent, shouldAttachToParentImmediately);
        NewsHolder viewHolder = new NewsHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MarketsAdapter.NewsHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mMarkets.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView price;
        TextView market;

        public NewsHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.description);
            market = (TextView) itemView.findViewById(R.id.publishedAt);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            name.setText("Name: " + mMarkets.get(listIndex).getName());
            price.setText("Price: " + mMarkets.get(listIndex).getPrice());
            market.setText("Market: " + mMarkets.get(listIndex).getMarket());

        }

        @Override
        public void onClick(View v) {
//            String url = mMarket.get(getAdapterPosition()).getUrl();
//
//            Intent i = new Intent(Intent.ACTION_VIEW);
//            i.setData(Uri.parse(url));
//            mContext.startActivity(i);
        }
    }

}