package com.example.endproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBvoters extends SQLiteOpenHelper {

    public static final String DBNAME = "voters.db";

    public DBvoters(Context context) {
        super(context,"voters.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table voters(username TEXT primary key,voter_id INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists voters");
        onCreate(db);
    }
    public Boolean insertvoterid(String username,int voterid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Integer intObj = new Integer(voterid);
        contentValues.put("username",username);
        contentValues.put("voter_id",intObj);
        long result=MyDB.insert("voters",null,contentValues);
        if(result==-1) return false;
        else return true;
    }
    public Boolean checkusernameandvoter(String username){
        SQLiteDatabase MyDB= this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from voters where username = ?",new String[]{username});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}
