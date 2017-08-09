package com.lucaslz.criminalintent.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lucaslz.criminalintent.storage.CrimeDbSchema.CrimeTable;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/8.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper {

    public static final String TAG = "CrimeBaseHelper";

    private static final int VERSION = 2;

    public static final String DATABASE_NAME = "crimes.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableSQLV2());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion == 1) {
            renameOldTableToTemp(sqLiteDatabase);
            sqLiteDatabase.execSQL(createTableSQLV2());

            sqLiteDatabase.execSQL(new StringBuffer("INSERT INTO ")
                    .append(CrimeTable.NAME)
                    .append("(")
                    .append("_id, ")
                    .append(CrimeTable.Cols.UUID).append(", ")
                    .append(CrimeTable.Cols.TITLE).append(", ")
                    .append(CrimeTable.Cols.DATE).append(", ")
                    .append(CrimeTable.Cols.SOLVED)
                    .append(")")
                    .append(" SELECT _id, ")
                    .append(CrimeTable.Cols.UUID).append(", ")
                    .append(CrimeTable.Cols.TITLE).append(", ")
                    .append(CrimeTable.Cols.DATE).append(", ")
                    .append(CrimeTable.Cols.SOLVED)
                    .append(" FROM ")
                    .append(CrimeTable.TEMP_NAME)
                    .toString());

            deleteTempTables(sqLiteDatabase);
        }
    }

    private void renameOldTableToTemp(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                new StringBuffer("ALTER TABLE ")
                        .append(CrimeTable.NAME)
                        .append(" RENAME TO ")
                        .append(CrimeTable.TEMP_NAME).toString()
        );
    }

    private void deleteTempTables(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                new StringBuffer("DROP TABLE ")
                        .append(CrimeTable.TEMP_NAME)
                        .toString());
    }

    private String createTableSQLV1() {
        return new StringBuffer("CREATE TABLE ")
                .append(CrimeTable.NAME)
                .append("(")
                .append(" _id integer primary key autoincrement, ")
                .append(CrimeTable.Cols.UUID).append(", ")
                .append(CrimeTable.Cols.TITLE).append(", ")
                .append(CrimeTable.Cols.DATE).append(", ")
                .append(CrimeTable.Cols.SOLVED)
                .append(")")
                .toString();
    }

    /**
     * 新增SUSPECT字段。
     *
     * @return
     */
    private String createTableSQLV2() {
        return new StringBuffer("CREATE TABLE ")
                .append(CrimeTable.NAME)
                .append("(")
                .append(" _id integer primary key autoincrement, ")
                .append(CrimeTable.Cols.UUID).append(", ")
                .append(CrimeTable.Cols.TITLE).append(", ")
                .append(CrimeTable.Cols.DATE).append(", ")
                .append(CrimeTable.Cols.SOLVED).append(", ")
                .append(CrimeTable.Cols.SUSPECT)
                .append(")")
                .toString();
    }
}
