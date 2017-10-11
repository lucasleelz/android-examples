package com.lucaslz.uicomponentexamples.storage;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/11.
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
}
