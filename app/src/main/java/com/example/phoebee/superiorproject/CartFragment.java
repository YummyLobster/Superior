package com.example.phoebee.superiorproject;


        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import com.example.phoebee.superiorproject.db.GoodDB;
        import com.example.phoebee.superiorproject.model.Markets;

        import java.util.List;

public class CartFragment extends Fragment {

    private TextView mCartList;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Cart");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.nav_cart, container, false);

        mCartList = (TextView)view.findViewById(R.id.shoppinglist);

        // 建立資料庫物件
        GoodDB test=new GoodDB(view.getContext());
        // 如果資料庫是空的，就建立一些範例資料
        // 這是為了方便測試用的，完成應用程式以後可以拿掉
        if (test.getCount() == 0) {
            test.sample();
        }

        mCartList.setText("目前資料庫裡有"+String.valueOf(test.getCount()).toString()+"筆資料\n");
        // 取得所有記事資料
        List<Markets> items=test.getAll();
        for(Markets i:items){
            mCartList.append("\n第"+String.valueOf(i.getId()).toString()+"筆聊天紀錄\n");
            mCartList.append("name="+i.getName()+"\n");
            mCartList.append("Price="+String.valueOf(i.getPrice()).toString()+"\n");
        }
        test.close();

        return view;
    }

}
