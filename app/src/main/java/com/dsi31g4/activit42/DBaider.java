package com.dsi31g4.activit42;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBaider extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";
    // j'efface le factory et la version dans le constructeur et remplacer par 1 et null
    public DBaider(@Nullable Context context) {
        super(context, "Login.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table utilisateur(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists utilisateur");

    }
    public Boolean insertData(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = db.insert("utilisateur", null, contentValues);
        if(result==-1) {
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean checknom(String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from utilisateur where username = ?", new String[]{username});
        // lorque le nombre de item dans le cursor <0 donc il existe donc true et le vidéo il return false
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkmdp(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from utilisateur where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}

