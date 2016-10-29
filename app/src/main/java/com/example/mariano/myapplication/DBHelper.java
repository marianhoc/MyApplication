package com.example.mariano.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;

/**
 * Created by Mariano on 24/10/2016.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static class DatosTabla implements BaseColumns {
        public static final String TABLE_NAME = "chefs";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";



        private static final String TEXT_TYPE = " TEXT";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + DatosTabla.TABLE_NAME + " (" +
                        DatosTabla._ID + " INTEGER PRIMARY KEY," +
                        DatosTabla.COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.COLUMN_EMAIL + TEXT_TYPE + COMMA_SEP +
                        DatosTabla.COLUMN_PASSWORD + TEXT_TYPE + " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + DatosTabla.TABLE_NAME;

    }




    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE = "myDatabase.db";




    public DBHelper(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatosTabla.SQL_CREATE_ENTRIES );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);
        onCreate(db);
    }




}
