package com.example.namenumarology;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAssets {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase db;
    private static DatabaseAssets databaseAssets;
    //Cursor c=null;

    private DatabaseAssets(Context context){
        this.sqLiteOpenHelper=new DatabaseHelperClass(context);
    }

    public static DatabaseAssets getInstance(Context context){
        if(databaseAssets==null){
            databaseAssets=new DatabaseAssets(context);
        }
        return databaseAssets;
    }

    public  void open(){
        this.db=sqLiteOpenHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null){
            this.db.close();
        }
    }

    public boolean insertdata(String vcol2){
        //SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",vcol2);
        long res=db.insert("favnames",null,cv);
        if(res==-1){
            return false;
        }
        else {
            return true;

        }
    }
    public Cursor getdata(int val,String table){
        Cursor cus = db.rawQuery("select * from "+table+" where ID="+val,null);
        return cus;
    }

    public Cursor getdata(char x,String table){
        Cursor cus = db.rawQuery("select * from "+table+" where alpha=\""+x+"\"",null);
        return cus;
    }

    public Cursor getdata(String table){
        Cursor cus=db.rawQuery("select * from "+table,null);
        return cus;
    }
}
