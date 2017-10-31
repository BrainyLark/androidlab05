package com.archer.lab05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by archer on 2017-10-25.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "calculator.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE history(id integer primary key autoincrement, firstnum double, operation text, secondnum double, resultnum double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Integer eraseHistory() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("history", "id > ?", new String[]{ "0" });
    }

    public boolean insertData(double first, double second, String op, double res) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstnum", first);
        contentValues.put("operation", op);
        contentValues.put("secondnum", second);
        contentValues.put("resultnum", res);
        db.insert("history", null, contentValues);
        return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("Select * from history", null);
        return result;
    }
}
