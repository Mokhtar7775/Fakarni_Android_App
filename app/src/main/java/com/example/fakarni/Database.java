package com.example.fakarni;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Database extends SQLiteOpenHelper {
    private static final String dbName="data.db";
    public Database(Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String users = ("create table usersTable (id integer primary key autoincrement , fName TEXT, lName TEXT, userName TEXT, password TEXT, gender TEXT)");
        String tasks = ("create table taskTable (id integer, Name TEXT, Date TEXT, Time TEXT, Notes TEXT, FOREIGN KEY (id) REFERENCES usersTable (id) )");
        db.execSQL(tasks);
        db.execSQL(users);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists taskTable");
        db.execSQL("drop table if exists usersTable");
        onCreate(db);
    }

    public void insertTaskData(String name, String date, String time, String notes, int ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Name", name);
        contentValues.put("Date", date);
        contentValues.put("Time", time);
        contentValues.put("Notes", notes);
        contentValues.put("id",ID);

        db.insert("taskTable",null,contentValues);
    }
    //bta3t mokhtar
    Cursor readAllTaskData(int id){
        String query = "Select * From taskTable where id = '"+id+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db!= null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    Cursor readAllTaskData(String date, int id) {
        Cursor cursor = null;
        String query = "Select * From taskTable where Date = '" + date + "' and id = '" + id + "' ";
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    //Users Data Functions

    public void insertUsersData(String fName , String lName , String userName , String password , String gender) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("fName", fName);
        contentValues.put("lName", lName);
        contentValues.put("userName", userName);
        contentValues.put("password", password);
        contentValues.put("gender", gender);

        db.insert("usersTable", null, contentValues);

    }

    public boolean checkU_P(String UserName,String Password){
        SQLiteDatabase myDb=this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursorL=myDb.rawQuery("SELECT * FROM usersTable WHERE userName=? and password=?",new String[] {UserName,Password});
        return cursorL.getCount() > 0;
    }


    public boolean userNameCheck(String username) {
        SQLiteDatabase myDb=this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursorL=myDb.rawQuery("SELECT * FROM usersTable WHERE userName=?",new String[] {username});
        return cursorL.getCount() > 0;
    }

    public Cursor getID(String username) {
        Cursor cursorL;
        SQLiteDatabase myDb=this.getWritableDatabase();
        cursorL = myDb.rawQuery("SELECT id FROM usersTable WHERE userName=?",new String[] {username});
        return cursorL;
    }
    public Cursor getName(String username) {
        Cursor cursorL;
        SQLiteDatabase myDb=this.getWritableDatabase();
        cursorL = myDb.rawQuery("SELECT fName FROM usersTable WHERE userName=?",new String[] {username});
        return cursorL;
    }
}
