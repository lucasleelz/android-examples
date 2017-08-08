package com.lucaslz.criminalintent.storage;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.lucaslz.criminalintent.Crime;
import com.lucaslz.criminalintent.storage.CrimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/8/8.
 */
public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime() {
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
        Crime result = new Crime(UUID.fromString(uuidString));
        result.setTitle(title);
        result.setDate(new Date(date));
        result.setSolved(isSolved != 0);
        return result;
    }
}
