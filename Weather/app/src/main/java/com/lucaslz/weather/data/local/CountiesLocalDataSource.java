package com.lucaslz.weather.data.local;

import android.support.annotation.NonNull;

import com.lucaslz.weather.data.CountiesDataSource;
import com.lucaslz.weather.domain.County;

import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
public class CountiesLocalDataSource implements CountiesDataSource {

    private static volatile CountiesLocalDataSource INSTANCE;

    private CountiesDao mCountiesDao;

    private CountiesLocalDataSource(@NonNull CountiesDao countiesDao) {
        mCountiesDao = countiesDao;
    }

    public static CountiesLocalDataSource getInstance(@NonNull CountiesDao countiesDao) {
        if (INSTANCE == null) {
            synchronized (CountiesLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CountiesLocalDataSource(countiesDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void findCounties(@NonNull LoadCountiesCallback callback) {
        final List<County> counties = mCountiesDao.findTasks();
        if (counties.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onCountiesLoaded(counties);
        }
    }
}
