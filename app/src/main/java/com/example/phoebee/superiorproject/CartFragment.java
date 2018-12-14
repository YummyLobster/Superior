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
import android.widget.Toast;

import com.example.phoebee.superiorproject.db.GoodDB;
import com.example.phoebee.superiorproject.db.HistoryDB;
import com.example.phoebee.superiorproject.model.History;
import com.example.phoebee.superiorproject.model.Markets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CartFragment extends Fragment  implements ShoppingCartAdapter.OnRecyclerViewItemDeleteClickListener, ShoppingCartAdapter.OnRecyclerViewItemAddClickListener,
        ShoppingCartAdapter.OnRecyclerViewItemReduceClickListener {


    private EditText name;
    private ImageButton edit_name;
    private Button confirmButton;
    private TextView Sub, Total;
    private ShoppingCartAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Cart");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.nav_cart, container, false);

        // 建立資料庫物件
        GoodDB test=new GoodDB(view.getContext());

        // 取得所有記事資料
        List<Markets> items=test.getAll();

        edit_name = view.findViewById(R.id.note_name_edit);
        name = view.findViewById(R.id.note_name);
        Sub = view.findViewById(R.id.Sub);
        Total = view.findViewById(R.id.Total);
        confirmButton = view.findViewById(R.id.confirm);

        name.setText("List");
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new
                LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ShoppingCartAdapter(getActivity(), items, this, this, this);
        recyclerView.setAdapter(adapter);
        calculation();

        test.close();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setEnabled(true);
            }
        });
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HistoryDB history_list=new HistoryDB(view.getContext());
                int history_amount = history_list.getCount()+1;
                History history = new History((long) history_amount,name.getText().toString());
                history_list.insert(history);

                GoodDB test=new GoodDB(view.getContext());
                List<Markets> items=test.getAll();

                for (int i = 0; i < items.size(); i++) {
                   test.updateHistory(items.get(i),history_amount);
                }
                Sub.setText("Sub Total: $0");
                Total.setText("Total: $0");
                adapter.clearall();
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "success!", Toast.LENGTH_SHORT).show();
                test.close();
                history_list.close();
            }
        });

    }

    @Override
    public void onItemDeleteClick(View view, Markets goods) {
        GoodDB test=new GoodDB(view.getContext());
        test.delete(goods.getId());
        calculation();//新計算價格...
        adapter.notifyDataSetChanged();
        test.close();
    }

    @Override
    public void onItemAddClick(View view, Markets goods) {
        GoodDB test=new GoodDB(view.getContext());
        goods.setAmount(goods.getAmount() + 1);
        test.updateAmount( goods);
        calculation();//更新計算價格
        adapter.notifyDataSetChanged();
        test.close();
    }

    @Override
    public void onItemReduceClick(View view, Markets goods) {
        GoodDB test=new GoodDB(view.getContext());
        if(goods.getAmount()>0)
            goods.setAmount(goods.getAmount() - 1);
        test.updateAmount( goods);
        calculation();//更新計算價格
        adapter.notifyDataSetChanged();
        test.close();
    }



    public void calculation() {//計算金額 更新頁面
        GoodDB test=new GoodDB(this.getContext());
        List<Markets> items=test.getAll();

        double sub = 0;
        for (int i = 0; i < items.size(); i++) {
            sub += items.get(i).getPrice() * (double) items.get(i).getAmount();
        }
        double total = sub *1.09;
        Sub.setText("Sub Total: $" + String.valueOf(sub));
        Total.setText("Total: $" + String.valueOf(total));
        test.close();
    }

}
