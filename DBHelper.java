package com.FerrySaptawan.uas_202102277;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "projectnew1.db";
    private static final int DB_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table apoteker(kode_apoteker TEXT primary key, nama_apoteker TEXT,jenis_kelamin TEXT,alamat TEXT,telepon TEXT, no_izin_praktek TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS apoteker");
        onCreate(sqLiteDatabase);
    }


    public boolean insertDataApoteker(String kode, String nama, String jk, String alamat, String telepon, String Noizin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kode_apoteker", kode);
        values.put("nama_apoteker", nama);
        values.put("jenis_kelamin", jk);
        values.put("alamat", alamat);
        values.put("telepon", telepon);
        values.put("no_izin_praktek", Noizin);

        long result = db.insert("apoteker", null, values);
        db.close();

        return result != -1;
    }


    public boolean checkkode(String kode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM apoteker WHERE kode_apoteker=?", new String[]{kode});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }



    public Cursor tampildataapoteker() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM apoteker", null);
    }



    public boolean deleteDataApoteker(String kode) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("apoteker", "kode_apoteker=?", new String[]{kode});
        db.close();
        return result > 0;
    }




    public boolean updateDataApoteker(String kode, String nama, String jk, String alamat, String telepon, String Noizin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nama_apoteker", nama);
        values.put("jenis_kelamin", jk);
        values.put("alamat", alamat);
        values.put("telepon", telepon);
        values.put("no_izin_praktek", Noizin);

        int result = db.update("apoteker", values, "kode=?", new String[]{kode});
        db.close();
        return result > 0;
    }
}