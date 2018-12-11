package com.example.phoebee.superiorproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phoebee.superiorproject.R;
import com.example.phoebee.superiorproject.model.Markets;

import java.util.List;


public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder> {
    private static final String TAG = "ShoppingCartAdapter";
    private Context mContext;
    private List<Markets> mGoodsList;
    private OnRecyclerViewItemDeleteClickListener itemDeleteClickListener;
    private OnRecyclerViewItemAddClickListener itemAddClickListener;
    private OnRecyclerViewItemReduceClickListener itemReduceClickListener;

    public ShoppingCartAdapter(Context mContext, List<Markets> mGoodsList, OnRecyclerViewItemDeleteClickListener itemDeleteClickListener, OnRecyclerViewItemAddClickListener itemAddClickListener, OnRecyclerViewItemReduceClickListener itemReduceClickListener) {
        this.mContext = mContext;
        this.mGoodsList = mGoodsList;
        this.itemDeleteClickListener = itemDeleteClickListener;
        this.itemAddClickListener = itemAddClickListener;
        this.itemReduceClickListener = itemReduceClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Markets goods = mGoodsList.get(position);

        holder.itemView.setTag(goods);
        holder.tv_goods_order.setText(String.valueOf(position + 1) + ".");
        holder.tv_goods_name.setText(goods.getName());
        holder.tv_goods_number.setText(String.valueOf(goods.getAmount()));
        holder.tv_goods_price.setText("$" + goods.getPrice());

        Glide.with(mContext).load(goods.getMarkeImageUrl()).into(holder.iv_market_image);

        holder.ib_goods_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDeleteClickListener.onItemDeleteClick(v, goods);
                mGoodsList.remove(position);
            }
        });
        holder.ib_goods_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAddClickListener.onItemAddClick(v, goods);
            }
        });
        holder.ib_goods_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemReduceClickListener.onItemReduceClick(v, goods);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mGoodsList != null && mGoodsList.size() > 0) {
            return mGoodsList.size();
        }
        return 0;
    }

    public interface OnRecyclerViewItemDeleteClickListener {

        void onItemDeleteClick(View view, Markets goods);
    }

    public interface OnRecyclerViewItemAddClickListener {

        void onItemAddClick(View view, Markets goods);
    }

    public interface OnRecyclerViewItemReduceClickListener {

        void onItemReduceClick(View view, Markets goods);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_goods_order;
        public TextView tv_goods_name;
        public ImageView iv_market_image;
        public TextView tv_goods_number;
        public TextView tv_goods_price;
        public ImageButton ib_goods_delete;
        public ImageButton ib_goods_add;
        public ImageButton ib_goods_reduce;

        public ViewHolder(View view) {
            super(view);
            tv_goods_order = (TextView) view.findViewById(R.id.goods_order);
            tv_goods_name = (TextView) view.findViewById(R.id.goods_name);
            iv_market_image = (ImageView) view.findViewById(R.id.market_image);
            tv_goods_number = (TextView) view.findViewById(R.id.goods_number);
            tv_goods_price = (TextView) view.findViewById(R.id.goods_price);
            ib_goods_delete = (ImageButton) view.findViewById(R.id.goods_delete);
            ib_goods_add = (ImageButton) view.findViewById(R.id.goods_add);
            ib_goods_reduce = (ImageButton) view.findViewById(R.id.goods_reduce);
            ;
        }
    }
}
