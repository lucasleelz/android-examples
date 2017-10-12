package com.lucaslz.uicomponentexamples.storage;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/11.
 */
public class Storage {
    private static volatile Storage ourInstance = null;

    private AppDatabase mAppDatabase;
    private UserDao mUserDao;

    public static Storage getInstance(@NonNull Context context) {
        if (ourInstance == null) {
            synchronized (Storage.class) {
                if (ourInstance == null) {
                    ourInstance = new Storage(context);
                }
            }
        }
        return ourInstance;
    }

    private Storage(@NonNull Context context) {
        mAppDatabase = Room.databaseBuilder(context, AppDatabase.class, "demo").build();
        mUserDao = mAppDatabase.userDao();
    }

    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }

    public UserDao getUserDao() {
        return mUserDao;
    }
}
