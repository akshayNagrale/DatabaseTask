package com.andro.databasetask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME_DATA = "STUDENT";

    // Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CITY = "city";


    // database version
    private static final String DATABASE_NAME = "DATA";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase databaseHelper;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating table query
        String CREATE_TABLE = "create table " + TABLE_NAME_DATA + "(" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " + CITY + " TEXT);";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DATA);
        onCreate(db);

    }

    public void insert(String name, String city) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, name);
        contentValue.put(DatabaseHelper.CITY, city);
        database.insert(DatabaseHelper.TABLE_NAME_DATA, null, contentValue);
        database.close();
    }

    public List<DataObject> getAllUserData() {

        List<DataObject> userarraylist = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAME_DATA;
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DataObject object = new DataObject();
                object.setId(cursor.getInt(0));
                object.setName(cursor.getString(1));
                object.setCity(cursor.getString(2));

                userarraylist.add(object);
            } while (cursor.moveToNext());
        }
        return userarraylist;
    }

//    public Cursor fetch() {
//        String[] columns = new String[]{DatabaseHelper.NAME, DatabaseHelper.CITY};
//        Cursor cursor = databaseHelper.query(DatabaseHelper.TABLE_NAME_DATA, columns,
//                null, null, null, null, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//        }
//        return cursor;


    public int update(DataObject data) {
        SQLiteDatabase databaseHelper = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.NAME, data.getName());
        contentValues.put(DatabaseHelper.CITY, data.getCity());
        return databaseHelper.update(DatabaseHelper.TABLE_NAME_DATA, contentValues,
                DatabaseHelper.ID+"= ?",
                new String[]{String.valueOf(data.getId())});


    }
    public void delete(DataObject data) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(DatabaseHelper.TABLE_NAME_DATA, DatabaseHelper.ID + "=" + data.getId(), null);
    }

}
