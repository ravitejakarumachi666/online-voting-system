package com.example.endproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.sql.Types.INTEGER;

public class DBadmin extends SQLiteOpenHelper {

    public static final String DBNAME = "admin.db";

    public DBadmin(Context context) {
        super(context,"admin.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table admin(candidate TEXT primary key, count INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists admin");

        onCreate(db);
    }

    public void updatevotes(String candidate){
        SQLiteDatabase MyDB= this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("Select * from admin where candidate = ?",new String[]{candidate});
        int votes=( cursor.getColumnIndex("count"))+1;
        if(cursor.getCount()>=1){
            MyDB.execSQL("delete from admin where candidate =?",new String[]{candidate});
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("candidate", candidate);
        contentValues.put("count", votes);
        long result = MyDB.insert("admin",null,contentValues);
        return ;

    }
    public Cursor fetchData(){
        SQLiteDatabase myDB=this.getReadableDatabase();
        Cursor cursor = myDB.rawQuery("select * from admin",null,null);
        return cursor;
    }
}
