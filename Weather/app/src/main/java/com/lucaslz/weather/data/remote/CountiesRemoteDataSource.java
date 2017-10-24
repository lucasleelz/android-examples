package com.lucaslz.weather.data.remote;

import android.support.annotation.NonNull;

import com.lucaslz.weather.data.CountiesDataSource;
import com.lucaslz.weather.data.local.CountiesDao;
import com.lucaslz.weather.data.local.CountiesLocalDataSource;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
public class CountiesRemoteDataSource implements CountiesDataSource {

    private static volatile CountiesRemoteDataSource INSTANCE;

    private CountiesRemoteDataSource() {
    }

    public static CountiesRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (CountiesRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CountiesRemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void findCounties(@NonNull LoadCountiesCallback callback) {

    }
}
