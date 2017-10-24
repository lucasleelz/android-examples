package com.lucaslz.weather.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.lucaslz.weather.domain.City;
import com.lucaslz.weather.domain.County;
import com.lucaslz.weather.domain.Province;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/23.
 */
@Database(
        entities = {
                City.class,
                County.class,
                Province.class
        },
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CityDao mCityDao();

    public abstract CountiesDao mCountiesDao();

    public abstract ProvincesDao mProvincesDao();

    private static AppDatabase INSTANCE;
    private static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "weather.db")
                        .build();
            }
        }
        return INSTANCE;
    }
}
