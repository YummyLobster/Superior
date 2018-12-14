package com.example.phoebee.superiorproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.phoebee.superiorproject.db.GoodDB;
import com.example.phoebee.superiorproject.model.Markets;

import java.util.List;

public class History_list_item extends AppCompatActivity {
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list_item);
        test = (TextView) findViewById(R.id.test);
        Intent intent = getIntent();
        int num = intent.getIntExtra("num", 0);

        // 建立資料庫物件
        GoodDB goodDB=new GoodDB(this);

        // 取得所有記事資料
        List<Markets> items=goodDB.getHistory(num+1);
        test.setText("");
        for(Markets i:items){
            test.append("\nName:"+i.getName()+"\n");
            test.append("Price:"+String.valueOf(i.getPrice()).toString()+"\n");
            test.append("Amount:"+String.valueOf(i.getAmount()).toString()+"\n");
            test.append("Market:"+String.valueOf(i.getMarket()).toString()+"\n");
        }

        goodDB.close();
    }
}
