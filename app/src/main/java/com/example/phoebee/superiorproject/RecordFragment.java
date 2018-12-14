package com.example.phoebee.superiorproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.phoebee.superiorproject.db.GoodDB;
import com.example.phoebee.superiorproject.db.HistoryDB;
import com.example.phoebee.superiorproject.model.History;
import com.example.phoebee.superiorproject.model.Markets;

import java.util.ArrayList;
import java.util.List;

public class RecordFragment extends Fragment {

    private HistoryAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("History");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_record, container, false);

        // 建立資料庫物件
        HistoryDB historyDB=new HistoryDB(view.getContext());

        // 取得所有記事資料
        List<History>history=historyDB.getAll();

        RecyclerView recyclerView = view.findViewById(R.id.history_recycler);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HistoryAdapter(getActivity(), history);
        recyclerView.setAdapter(adapter);

        historyDB.close();
        return view;
    }

}
