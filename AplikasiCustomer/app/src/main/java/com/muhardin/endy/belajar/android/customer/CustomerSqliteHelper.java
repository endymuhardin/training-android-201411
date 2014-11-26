package com.muhardin.endy.belajar.android.customer;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomerSqliteHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE = "create table customer (" +
            "_id INTEGER PRIMARY KEY, " +
            "nama TEXT, " +
            "tgl_lahir INTEGER, " +
            "jenis_kelamin TEXT, " +
            "domisili TEXT, " +
            "alamat TEXT )";

    public CustomerSqliteHelper(Context context) {
        super(context, CustomerConstants.NAMA_DATABASE, null, CustomerConstants.VERSI_DATABASE);
    }

    public CustomerSqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // kosong saja, karena aplikasi versi pertama
    }

}
