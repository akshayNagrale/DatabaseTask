package com.andro.databasetask;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class DBManager {

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    private DatabaseHelper dbHelper = new DatabaseHelper(context);


    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert( String name, String city) {

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.CITY, city);
        database.insert(DatabaseHelper.TABLE_NAME_DATA, null, contentValue);
        database.close();

//        database = dbHelper.getWritableDatabase();
//        ContentValues contentValue = new ContentValues();
//        contentValue.put(DatabaseHelper.NAME, name);
//        contentValue.put(DatabaseHelper.CITY, city);
//        database.insert(DatabaseHelper.TABLE_NAME_DATA, null, contentValue);
    }

   /* public Cursor fetch() {
        String[] columns = new String[]{DatabaseHelper.ID, DatabaseHelper.NAME, DatabaseHelper.CITY};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME_DATA, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        public int update(int id, String name, String city) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(DatabaseHelper.ID,_id);
            contentValues.put(DatabaseHelper.NAME, name);
            contentValues.put(DatabaseHelper.CITY, city);
            int i = database.update(DatabaseHelper.TABLE_NAME_DATA, contentValues, DatabaseHelper.ID + " = " + _id, null);
            return i;
        }

        public void delete(int _id) {
            database.delete(DatabaseHelper.TABLE_NAME_DATA, DatabaseHelper.ID + "=" + _id, null);
        }

*/

    }
