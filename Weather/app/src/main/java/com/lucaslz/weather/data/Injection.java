package com.lucaslz.weather.data;

import android.content.Context;

import com.lucaslz.weather.data.local.AppDatabase;
import com.lucaslz.weather.data.local.ProvincesLocalDataSource;
import com.lucaslz.weather.data.remote.ProvincesRemoteDataSource;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/25.
 */
public class Injection {

    public static ProvincesRepository provideProvincesRepository(Context context) {
        AppDatabase database = AppDatabase.getInstance(context);
        return ProvincesRepository.getInstance(
                ProvincesLocalDataSource.getInstance(database.mProvincesDao()),
                ProvincesRemoteDataSource.getInstance()
        );
    }
}
