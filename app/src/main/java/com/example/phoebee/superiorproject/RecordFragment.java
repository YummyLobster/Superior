package com.example.phoebee.superiorproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phoebee.superiorproject.db.NoteLitepal;
import com.example.phoebee.superiorproject.model.Note;

import java.util.ArrayList;
import java.util.List;

public class RecordFragment extends Fragment {

    private List<Note> noteList = new ArrayList<Note>();
    private RecordAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Record");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nav_record, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.record_recycler);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        noteList = NoteLitepal.queryNoteAll();
        adapter = new RecordAdapter(getActivity(), noteList);
        recyclerView.setAdapter(adapter);
        return view;
    }

}
