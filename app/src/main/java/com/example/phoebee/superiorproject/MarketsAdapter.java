package com.example.phoebee.superiorproject;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.phoebee.superiorproject.db.GoodDB;
import com.example.phoebee.superiorproject.model.ItemClickListener;
import com.example.phoebee.superiorproject.model.Markets;
import com.squareup.picasso.Picasso;

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
        ImageView image;

        public NewsHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.description);
            market = (TextView) itemView.findViewById(R.id.publishedAt);
            image = (ImageView) itemView.findViewById(R.id.image);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            //Use Picasso to load the image located at "urlToImage" in each RecyclerView Item
            String imageUrl = mMarkets.get(listIndex).getImage();
            if (imageUrl != null) {
                Picasso.get().load(imageUrl).into(image);
            }
            name.setText(mMarkets.get(listIndex).getName());
            price.setText("$ " + mMarkets.get(listIndex).getPrice());
            market.setText(mMarkets.get(listIndex).getMarket());

        }

        @Override
        public void onClick(View v) {
            String iName = mMarkets.get(getAdapterPosition()).getName();
            double iPrice = mMarkets.get(getAdapterPosition()).getPrice();
            String iCategory = mMarkets.get(getAdapterPosition()).getCategory();
            String iImage = mMarkets.get(getAdapterPosition()).getImage();
            String iMarket = mMarkets.get(getAdapterPosition()).getMarket();

            Markets item = new Markets(iName,iPrice,iCategory,iImage,iMarket);

            GoodDB gooddb = new GoodDB(v.getContext());
            gooddb.insert(item);
            Toast.makeText(v.getContext(), "Add " + iName ,
                    Toast.LENGTH_LONG).show();
        }
    }

}