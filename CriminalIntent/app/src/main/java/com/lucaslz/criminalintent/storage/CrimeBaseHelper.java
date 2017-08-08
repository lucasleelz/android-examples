package com.lucaslz.criminalintent.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.lucaslz.criminalintent.storage.CrimeDbSchema.CrimeTable;

import java.util.logging.Logger;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/8.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "CrimeBaseHelper";

    private static final int VERSION = 1;

    public static final String DATABASE_NAME = "crimes.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = new StringBuffer("CREATE TABLE ")
                .append(CrimeTable.NAME)
                .append("(")
                .append(" _id integer primary key autoincrement, ")
                .append(CrimeTable.Cols.UUID).append(", ")
                .append(CrimeTable.Cols.TITLE).append(", ")
                .append(CrimeTable.Cols.DATE).append(", ")
                .append(CrimeTable.Cols.SOLVED)
                .append(")")
                .toString();

        Log.d(TAG, sql);
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
