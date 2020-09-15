package com.example.personalmanagementapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DataBaseManager {

    public static final String DB_NAME = "FriendInfo";
    public static final String DB_TABLE = "Friend";
    public static final int DB_VERSION = 1;
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (FirstName TEXT, LastName TEXT, gender TEXT, age INTEGER, address TEXT, taskName TEXT, location TEXT, status TEXT );";
    private SQLHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public DataBaseManager(Context c) {
        this.context = c;
        helper = new SQLHelper(c);
        this.db = helper.getWritableDatabase();
    }

    public DataBaseManager openReadable() throws android.database.SQLException {
        helper = new SQLHelper(context);
        db = helper.getReadableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    public boolean addRow( String f, String l, Integer a, String ad, String tn, String lo) {
        synchronized(this.db) {
            ContentValues newFriend = new ContentValues();
            newFriend.put("fname", f);
            newFriend.put("lname", l);

            newFriend.put("age", a);
            newFriend.put("address",ad);
            newFriend.put("taskName",tn);
            newFriend.put("location",lo);


            try {
                db.insertOrThrow(DB_TABLE, null, newFriend);
            } catch (Exception e) {
                Log.e("Error in inserting records", e.toString());
                e.printStackTrace();
                return false;
            }
            //db.close();
            return true;
        }
    }

    public ArrayList<String> ViewRecords() {
        ArrayList<String> FriendRows = new ArrayList<String>();
        String[] columns = new String[] {"fname", "lname","age","address","taskName","location"};
        Cursor cursor = db.query(DB_TABLE, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            FriendRows.add((cursor.getString(0)) + ", " + cursor.getString(1) + ", " + cursor.getString(2) +","+Integer.toString(cursor.getInt(3))+"," + cursor.getString(4) + "," +cursor.getString(5)+","+cursor.getString(6)+","+cursor.getString(7));
            cursor.moveToNext();
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return FriendRows;
    }

    public void deleteRecords()
    {
        db = helper.getWritableDatabase();
        db.delete(DB_TABLE, null, null);
    }



    public class SQLHelper extends SQLiteOpenHelper {
        public SQLHelper (Context c) {
            super(c, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w("Friends table", "Upgrading database i.e. dropping table and re-creating it");
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }
}

