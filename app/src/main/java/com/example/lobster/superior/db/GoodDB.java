package com.example.lobster.superior.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class GoodDB {
    private static String DATABASE_TABLE = "goods";
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    // Button元件的事件處理 - 插入記錄
    public void btn1_Click(int _id,String name, int price, String category, String image, String market) {
        long id;
        ContentValues cv = new ContentValues();
        cv.put("_id", _id);
        cv.put("name", name);
        cv.put("price", price);
        cv.put("category", category);
        cv.put("image", image);
        cv.put("market", market);
        id = db.insert(DATABASE_TABLE, null, cv);
    }  // 更新記錄
//    public void btn2_Click(View view) {
//        int count;
//        int id = Integer.parseInt(txtID.getText().toString());
//        ContentValues cv = new ContentValues();
//        cv.put("grade", Double.parseDouble(txtNewGrade.getText().toString()));
//        count = db.update(DATABASE_TABLE, cv, "_id=" + id, null);
//        output.setText("更新記錄成功: " + count);
//        command.setText("UPDATE Student Set grade
//                        ="+  txtNewGrade.getText().toString() + "WHERE id = "+
//                txtID.getText().toString());
//    }  // 刪除記錄
//    public void btn3_Click(View view) {
//        int count;
//        int id = Integer.parseInt(txtID.getText().toString());
//        count = db.delete(DATABASE_TABLE, "_id=" + id, null);
//        output.setText("刪除記錄成功: " + count);
//        command.setText("Delete From Student Where id  = "+ txtID.getText().toString());
//    }  // 查詢所有記錄
//    public void btn4_Click(View view) {
//        // 查詢整個資料表
//        SqlQuery("SELECT * FROM " + DATABASE_TABLE);
//        command.setText("SELECT * FROM  Student");
//    }
//    public void btn5_Click(View view) {
//        EditText txtSQL = (EditText) findViewById(R.id.txtSQL);
//        // 執行輸入SQL指令的查詢
//        SqlQuery(txtSQL.getText().toString());
//    }
//    // 執行SQL查詢
//    public void SqlQuery(String sql) {
//        String[] colNames;
//        String str = "";
//        Cursor c = db.rawQuery(sql, null);
//        colNames = c.getColumnNames();
//        // 顯示欄位名稱
//        for (int i = 0; i < colNames.length; i++)
//            str += colNames[i] + "\t\t";
//        str += "\n";
//        c.moveToFirst();  // 第1筆
//        // 顯示欄位值
//        for (int i = 0; i < c.getCount(); i++) {
//            str += c.getString(0) + "\t\t";
//            str += c.getString(1) + "\t\t";
//            str += c.getString(2) + "\n";
//            c.moveToNext();  // 下一筆
//        }
//        output.setText(str.toString());
//    }
}
