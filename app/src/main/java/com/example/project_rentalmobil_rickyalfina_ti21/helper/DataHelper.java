package com.example.project_rentalmobil_rickyalfina_ti21.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "rentalmobil.db";
    private static final int DATABASE_VERSION = 1;

    public DataHelper(Context context) {
        super(context, "rentalmobil.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("PRAGMA foreign_keys=ON");
        db.execSQL("create Table user(email TEXT primary key, password TEXT)");
        db.execSQL("create table penyewa (" +
                "nama text," +
                "alamat text," +
                "no_hp text," +
                "primary key(nama)" +
                ");" +
                "");
        db.execSQL("create table mobil(" +
                "merk text," +
                "harga int," +
                "primary key(merk)" +
                ");" +
                "");
        db.execSQL("create table sewa(" +
                "merk text," +
                "nama text," +
                "promo int," +
                "lama int," +
                "total double," +
                "foreign key(merk) references mobil (merk), " +
                "foreign key(nama) references penyewa (nama) " +
                ");" +
                "");

        db.execSQL("insert into mobil values (" +
                "'Avanza'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Xenia'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Ertiga'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'APV'," +
                "400000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Innova'," +
                "500000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Xpander'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Pregio'," +
                "550000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Elf'," +
                "700000" +
                ");" +
                "");
        db.execSQL("insert into mobil values (" +
                "'Alphard'," +
                "1500000" +
                ");" +
                "");
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<String>();
        String selectQuery = "select * from mobil";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                categories.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return categories;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop Table if exists user");
    }

    public Boolean insertData(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert("user", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ?", new String[]{email});

        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
}