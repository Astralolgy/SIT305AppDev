package com.example.lostandfound;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LostFoundItemDB  extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "LostFoundItems";
    private static final String DB_NAME = "LostFoundItemDB.db";

    public LostFoundItemDB(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlDB = "CREATE TABLE LostFoundItems (id TEXT PRIMARY KEY, type TEXT, itemName TEXT, contact TEXT, description TEXT, time TEXT, location TEXT)";
        db.execSQL(sqlDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean addItem(LostFoundItem lostfoundItem)
    {
        SQLiteDatabase sql_DB = getWritableDatabase();
        ContentValues cal = new ContentValues();
        cal.put("id", lostfoundItem.getId());
        cal.put("type", lostfoundItem.getType());
        cal.put("itemName", lostfoundItem.getItemName());
        cal.put("contact", lostfoundItem.getContact());
        cal.put("description", lostfoundItem.getDescription());
        cal.put("time", lostfoundItem.getTime());
        cal.put("location", lostfoundItem.getLocation());

        Log.i("1",lostfoundItem.getDescription());



        long rowId = sql_DB.insert(TABLE_NAME, null, cal);
        sql_DB.close();

        if (rowId > -1)
        {
            System.out.println("Item Posted!" + rowId);
            return true;
        }
        else
        {
            System.out.println("Insert Failed | Error");
            return false;
        }
    }

    public Boolean deleteTask(LostFoundItem lostFoundItem) {
        SQLiteDatabase sql_DB = getWritableDatabase();

        long rowId = sql_DB.delete(TABLE_NAME, "id = ?", new String[]{lostFoundItem.getId()});

        sql_DB.close();

        if (rowId > -1) {
            System.out.println("Item Removed" + rowId);
            return true;
        } else {
            System.out.println("Removal Failed | Error");
            return false;
        }
    }

    public LostFoundItem getItems(String id)
    {
        SQLiteDatabase sql_DB = this.getReadableDatabase();
        Cursor query = sql_DB.query(TABLE_NAME, new String[] {"id", "type", "itemName", "contact", "description", "time", "location"},
                "id=?", new String[]{id}, null, null, null, null);
        if (query != null)
        {
            query.moveToFirst();
        }
        LostFoundItem lostFoundItem = new LostFoundItem(query.getString(0), query.getString(1), query.getString(2), query.getString(3), query.getString(4), query.getString(5), query.getString(6));
        query.close();
        sql_DB.close();
        return lostFoundItem;
    }

    public List<LostFoundItem> getAllItem()
    {
        SQLiteDatabase sql_DB = this.getReadableDatabase();
        Cursor query = sql_DB.query(TABLE_NAME, null, null, null, null, null, null);
        List<LostFoundItem> result = new ArrayList<>();

        while(query.moveToNext())
        {
            result.add(new LostFoundItem(query.getString(0), query.getString(1), query.getString(2), query.getString(3), query.getString(4), query.getString(5), query.getString(6)));
        }
        query.close();
        sql_DB.close();
        return result;
    }
}
