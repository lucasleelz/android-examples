package com.lucaslz.uicomponentexamples.storage;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/11.
 */
public class DatabaseService {
    private static volatile DatabaseService ourInstance = null;
    public AppDatabase mAppDatabase;
    public static DatabaseService getInstance(@NonNull Context context) {
        if (ourInstance == null) {
            synchronized (DatabaseService.class) {
                if (ourInstance == null) {
                    ourInstance = new DatabaseService(context);
                }
            }
        }
        return ourInstance;
    }

    private DatabaseService(@NonNull Context context) {
        mAppDatabase = Room.databaseBuilder(context, AppDatabase.class, "demo").build();
    }
}
