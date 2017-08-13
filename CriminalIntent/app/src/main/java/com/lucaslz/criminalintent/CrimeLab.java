package com.lucaslz.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lucaslz.criminalintent.storage.CrimeBaseHelper;
import com.lucaslz.criminalintent.storage.CrimeCursorWrapper;
import com.lucaslz.criminalintent.storage.CrimeDbSchema.CrimeTable;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/6.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;

    private Context mContext;

    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public List<Crime> getCrimes() {
        List<Crime> results = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                results.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close(); // 必须的。
        }

        return results;
    }

    public Crime getCrime(UUID id) {
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + " = ? ",
                new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void addCrime(Crime crime) {
        mDatabase.insert(
                CrimeTable.NAME,
                null,
                getContentValues(crime)
        );
    }

    public void updateCrime(Crime crime) {
        String uuidString = crime.getId().toString();
        mDatabase.update(
                CrimeTable.NAME,
                getContentValues(crime),
                CrimeTable.Cols.UUID + " = ? ",
                new String[]{uuidString});
    }

    public File getPhotoFile(Crime crime) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, crime.getPhotoFilename());
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues result = new ContentValues();
        result.put(CrimeTable.Cols.UUID, crime.getId().toString());
        result.put(CrimeTable.Cols.TITLE, crime.getTitle());
        result.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        result.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? Crime.SOLVED : Crime.UNSOLVED);
        result.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());
        return result;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }
}
