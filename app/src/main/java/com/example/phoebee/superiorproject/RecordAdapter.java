package com.example.phoebee.superiorproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.phoebee.superiorproject.R;
import com.example.phoebee.superiorproject.model.Note;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {
    private static final String TAG = "RecordAdapter";
    private Context mContext;
    private List<Note> mNoteList;

    public RecordAdapter(Context mContext, List<Note> mNoteList) {
        this.mContext = mContext;
        this.mNoteList = mNoteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.record_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Note note = mNoteList.get(position);
        holder.itemView.setTag(note);
        holder.tv_name.setText(note.getName());
        holder.tv_time.setText(note.getTime());
        holder.tv_money.setText(note.getMoney());
    }

    @Override
    public int getItemCount() {
        if (mNoteList != null && mNoteList.size() > 0) {
            return mNoteList.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public TextView tv_time;
        public TextView tv_money;

        public ViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.name);
            tv_time = (TextView) view.findViewById(R.id.time);
            tv_money = (TextView) view.findViewById(R.id.money);
        }
    }
}
