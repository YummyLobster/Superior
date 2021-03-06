package com.example.phoebee.superiorproject.db;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.phoebee.superiorproject.model.Markets;


// 資料功能類別
public class GoodDB {
    // 表格名稱
    public static final String TABLE_NAME = "shoppinglist";
    // 編號表格欄位名稱，固定不變
    public static final String KEY_ID = "_id";
    // 其它表格欄位名稱
    public static final String NAME_COLUMN = "name";
    public static final String PRICE_COLUMN = "price";
    public static final String CATEGORY_COLUMN = "category";
    public static final String AMOUNT_COLUMN = "amount";
    public static final String IMAGE_COLUMN = "image";
    public static final String MARKET_COLUMN = "market";
    public static final String HISTORY_COLUMN = "history_id";
    // 使用上面宣告的變數建立表格的SQL指令
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME_COLUMN + " TEXT NOT NULL, " +
                    PRICE_COLUMN + " REAL NOT NULL, " +
                    CATEGORY_COLUMN + " TEXT NOT NULL, " +
                    AMOUNT_COLUMN + " TEXT NOT NULL, " +
                    IMAGE_COLUMN + " TEXT NOT NULL, " +
                    MARKET_COLUMN + " TEXT NOT NULL, " +
                    HISTORY_COLUMN + " INTEGER)";
    // 資料庫物件
    private SQLiteDatabase db;

    // 建構子，一般的應用都不需要修改
    public GoodDB(Context context) {
        db = DBHelper.getDatabase(context);
    }

    // 關閉資料庫，一般的應用都不需要修改
    public void close() {
        db.close();
    }

    // 新增參數指定的物件
    public Markets insert(Markets item) {
        // 建立準備新增資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的新增資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(NAME_COLUMN, item.getName());
        cv.put(PRICE_COLUMN, item.getPrice());
        cv.put(CATEGORY_COLUMN, item.getCategory());
        cv.put(AMOUNT_COLUMN, item.getAmount());
        cv.put(IMAGE_COLUMN, item.getImage());
        cv.put(MARKET_COLUMN, item.getMarket());
        // 新增一筆資料並取得編號
        // 第一個參數是表格名稱
        // 第二個參數是沒有指定欄位值的預設值
        // 第三個參數是包裝新增資料的ContentValues物件
        long id = db.insert(TABLE_NAME, null, cv);
        // 設定編號
        item.setId(id);
        // 回傳結果
        return item;
    }

    // 修改參數指定的物件
    public boolean update(Markets item) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(NAME_COLUMN, item.getName());
        cv.put(PRICE_COLUMN, item.getPrice());
        cv.put(CATEGORY_COLUMN, item.getCategory());
        cv.put(AMOUNT_COLUMN, item.getAmount());
        cv.put(IMAGE_COLUMN, item.getImage());
        cv.put(MARKET_COLUMN, item.getMarket());
        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.getId();
        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }
    public boolean updateAmount(Markets item) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(AMOUNT_COLUMN, item.getAmount());
        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.getId();
        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }
    public boolean updateHistory(Markets item, int history_id) {
        // 建立準備修改資料的ContentValues物件
        ContentValues cv = new ContentValues();
        // 加入ContentValues物件包裝的修改資料
        // 第一個參數是欄位名稱， 第二個參數是欄位的資料
        cv.put(HISTORY_COLUMN, history_id);
        // 設定修改資料的條件為編號
        // 格式為「欄位名稱＝資料」
        String where = KEY_ID + "=" + item.getId();
        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, cv, where, null) > 0;
    }

    // 刪除參數指定編號的資料
    public boolean delete(long id) {
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where, null) > 0;
    }

    // 讀取所有記事資料
    public List<Markets> getAll() {
        List<Markets> result = new ArrayList<Markets>();
        String where = HISTORY_COLUMN + " IS NULL";
        //游標指向該資料表
        Cursor cursor = db.query(TABLE_NAME, null, where, null, null, null, null, null);
        //將所有資料轉成Item並添加進List
        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }
        //關閉游標
        cursor.close();
        return result;
    }

    // 取得指定編號的資料物件
    public Markets get(long id) {
        // 準備回傳結果用的物件
        Markets item = null;
        // 使用編號為查詢條件
        String where = KEY_ID + "=" + id;
        // 執行查詢
        Cursor result = db.query(TABLE_NAME, null, where, null, null, null, null, null);
        // 如果有查詢結果
        if (result.moveToFirst()) {
            // 讀取包裝一筆資料的物件
            item = getRecord(result);
        }
        // 關閉Cursor物件
        result.close();
        // 回傳結果
        return item;
    }
    public List<Markets> getHistory(int id) {
        List<Markets> result = new ArrayList<Markets>();
        String where = HISTORY_COLUMN + "=" + id;
        //游標指向該資料表
        Cursor cursor = db.query(TABLE_NAME, null, where, null, null, null, null, null);
        //將所有資料轉成Item並添加進List
        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }
        //關閉游標
        cursor.close();
        return result;
    }

    // 把游標Cursor取得的資料轉換成目前的資料包裝為物件
    public Markets getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Markets result = new Markets();
        result.setId(cursor.getLong(0));
        result.setName(cursor.getString(1));
        result.setPrice(cursor.getDouble(2));
        result.setCategory(cursor.getString(3));
        result.setAmount(cursor.getInt(4));
        result.setImage(cursor.getString(5));
        result.setMarket(cursor.getString(6));
        //result.setHistory_id(cursor.getInt(7));
        return result;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }
        return result;
    }

}

