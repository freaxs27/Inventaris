package com.vsgaa.aplikasisqlite.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "inventaris.db";
    private static final int DB_VERSION = 1;

    private final String TABLE_NAME = "sqlite";
    private final String COLUMN_ID = "id";
    private final String COLUMN_NAME = "name";
    private final String COLUMN_HARGA = "harga";
    private final String COLUMN_JUMLAH = "jumlah";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_HARGA + " TEXT NOT NULL, " +
                COLUMN_JUMLAH + " TEXT NOT NULL )";

        sqLiteDatabase.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long insert(String name, String harga, String jumlah) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_HARGA, harga);
        values.put(COLUMN_JUMLAH, jumlah);
        long rowId = db.insert(TABLE_NAME, null, values);
        db.close();
        return rowId;
    }

    public int update(int id, String name, String harga, String jumlah) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_JUMLAH, harga);
        values.put(COLUMN_JUMLAH, jumlah);
        int rowAffected = db.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(id)});
        db.close();
        Log.d("DBHelper","row affected: "+rowAffected+" "+name+" "+harga+""+jumlah);
        return rowAffected;
    }

    public int delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowAffected = db.delete(TABLE_NAME,"id=?",new String[]{String.valueOf(id)});
        db.close();
        return rowAffected;
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        ArrayList<HashMap<String, String>> data = new ArrayList<>();
        String kueri = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(kueri, null);
        while (cursor.moveToNext()){
            HashMap<String,String> map = new HashMap<>();
            map.put(COLUMN_ID, cursor.getString(0));
            map.put(COLUMN_NAME, cursor.getString(1));
            map.put(COLUMN_HARGA, cursor.getString(1));
            map.put(COLUMN_JUMLAH, cursor.getString(2));
            data.add(map);
        }
        db.close();
        return data;
    }
}
