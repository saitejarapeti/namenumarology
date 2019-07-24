package com.example.namenumarology;

import android.content.Context;


import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseHelperClass extends SQLiteAssetHelper {
    public static final String database="result8.db";
    /*public static final String destiny_table="Destiny";
    public static final String hearts_table="Soul_Urge";
    public static final String personality_table="Personality";
    public static final String hidden_table="Hidden_Passion";
    public static final String karma_table="Karma";
    public static final String cc_table="Cornerstone_Capstone";
    public static final String favname_table="favnames";
    public static final String col1="ID";
    public static final String col2="name";*/

    public DatabaseHelperClass(Context context) {

        super(context, database, null, 1);

    }
        //i will use external database whic is already created so ill comment this out
    /*
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+destiny_table+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,message TEXT)");
        db.execSQL("create table "+hearts_table+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,message TEXT)");
        db.execSQL("create table "+personality_table+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,message TEXT)");
        db.execSQL("create table "+hidden_table+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,message TEXT)");
        db.execSQL("create table "+karma_table+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,message TEXT)");
        db.execSQL("create table "+cc_table+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,message TEXT,alpha Text)");
        db.execSQL("create table "+favname_table+"("+col1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+col2+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+destiny_table);
        db.execSQL("DROP TABLE IF EXISTS "+hearts_table);
        db.execSQL("DROP TABLE IF EXISTS "+personality_table);
        db.execSQL("DROP TABLE IF EXISTS "+hidden_table);
        db.execSQL("DROP TABLE IF EXISTS "+karma_table);
        db.execSQL("DROP TABLE IF EXISTS "+cc_table);
        db.execSQL("DROP TABLE IF EXISTS "+favname_table);
        onCreate(db);
    }

    public boolean insertdata(String vcol2){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(col2,vcol2);
        long res=db.insert(favname_table,null,cv);
        if(res==-1){
            return false;
        }
        else {
            return true;

        }
    }
    public Cursor getdata(int val,String table){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cus = db.rawQuery("select * from "+table+" where ID="+val,null);
        return cus;
    }

    public Cursor getdata(char x,String table){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cus = db.rawQuery("select * from "+table+" where alpha=\""+x+"\"",null);
        return cus;
    }

    public Cursor getdata(String table){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cus=db.rawQuery("select * from "+table,null);
        return cus;
    }*/
}
