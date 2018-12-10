//package com.example.phoebee.superiorproject;
//
//
//import android.content.Context;
//import android.support.v4.app.Fragment;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//import com.example.phoebee.superiorproject.db.GoodDB;
//import com.example.phoebee.superiorproject.model.ItemClickListener;
//import com.example.phoebee.superiorproject.model.Markets;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class RecyclerCartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
//
//    private ItemClickListener itemClickListener;
//
//    public RecyclerCartViewHolder(View itemView) {
//        super(itemView);
//        itemView.setOnClickListener(this);
//        itemView.setOnLongClickListener(this);
//    }
//
//    public void setItemClickListener(ItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//    }
//
//    @Override
//    public void onClick(View v) {
//        itemClickListener.onClick(v, getAdapterPosition(), false);
//    }
//
//    @Override
//    public boolean onLongClick(View v) {
//        itemClickListener.onClick(v, getAdapterPosition(), true);
//        return true;
//    }
//}
//
//public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ItemsHolder> {
//    Fragment mFragment;
//    List<Markets> mMarkets;
//
//    public CartAdapter(CartFragment fragment, List<Markets> markets) {
//        this.mFragment = fragment;
//        this.mMarkets = markets;
//    }
//
//    @Override
//    public CartAdapter.ItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//        boolean shouldAttachToParentImmediately = false;
//
//        View view = inflater.inflate(R.layout.cart_items, parent, shouldAttachToParentImmediately);
//        ItemsHolder viewHolder = new ItemsHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(CartAdapter.ItemsHolder holder, int position) {
//        holder.bind(position);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mMarkets.size();
//    }
//
//    class ItemsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        TextView name;
//        TextView price;
//        TextView market;
//
//        public ItemsHolder(View itemView) {
//            super(itemView);
//            name = (TextView) itemView.findViewById(R.id.cart_name);
//            price = (TextView) itemView.findViewById(R.id.cart_price);
//            market = (TextView) itemView.findViewById(R.id.cart_amount);
//            itemView.setOnClickListener(this);
//        }
//
//        void bind(int listIndex) {
//            name.setText( mMarkets.get(listIndex).getName());
//            price.setText( "" + mMarkets.get(listIndex).getPrice());
//            market.setText(mMarkets.get(listIndex).getMarket());
//
//        }
//
//        @Override
//        public void onClick(View v) {
//            String iName = mMarkets.get(getAdapterPosition()).getName();
//            Toast.makeText(v.getContext(), "Add " + iName ,
//                    Toast.LENGTH_LONG).show();
//        }
//    }
//
//}