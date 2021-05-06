package com.example.demoapp.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

   public static final String DATABASE_NAME = "MyDBName.db";
   public static final String CONTACTS_TABLE_NAME = "history";
   public static final String COLUMN_CHECKED = "checked";
   private HashMap hp;

   public DBHelper(Context context) {
      super(context, DATABASE_NAME , null, 1);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      // TODO Auto-generated method stub
      db.execSQL("create table history (id INTEGER PRIMARY KEY AUTOINCREMENT,created_at DATETIME DEFAULT CURRENT_TIMESTAMP)");
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // TODO Auto-generated method stub
      db.execSQL("DROP TABLE IF EXISTS history");
      onCreate(db);
   }

   public boolean insertContact () {
      SQLiteDatabase db = this.getWritableDatabase();
//      db.execSQL("INSERT INTO history( created_at) VALUES('datetime()')");
      SimpleDateFormat dateFormat = new SimpleDateFormat(
              "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
      Date date = new Date();
      ContentValues contentValues = new ContentValues();
      contentValues.put("created_at", dateFormat.format(date));

      db.insert("history", null, contentValues);
      return true;
   }
   
   public int getMonth(String month) {
      SQLiteDatabase db = this.getReadableDatabase();
      if(Integer.parseInt(month)<10){
         month="0"+month;
      }
      String sql="SELECT * FROM history where strftime('%m',created_at)='"+month+"'";
      Cursor res =  db.rawQuery(sql , null );
      Log.e("Sql-->", "getMonth: "+sql );
      return res.getCount();
   }

   public int getWeek() {
      SQLiteDatabase db = this.getReadableDatabase();

      String sql="SELECT * FROM history where strftime('%w',created_at) BETWEEN '0' AND '6'";
      Cursor res =  db.rawQuery(sql , null );
      Log.e("Sql-->", "getWeek: "+sql );
      return res.getCount();
   }

   public int getDay(String day) {
      SQLiteDatabase db = this.getReadableDatabase();
      if(Integer.parseInt(day)<10){
         day="0"+day;
      }
      String sql="SELECT * FROM history where strftime('%d',created_at)='"+day+"'";
      Cursor res =  db.rawQuery( sql, null );
      Log.e("Sql-->", "getDay: "+sql );
      return res.getCount();
   }
   public int numberOfRows(){
      SQLiteDatabase db = this.getReadableDatabase();
      int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
      return numRows;
   }
   

}