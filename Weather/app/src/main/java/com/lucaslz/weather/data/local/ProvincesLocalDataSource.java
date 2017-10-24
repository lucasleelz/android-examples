package com.lucaslz.weather.data.local;

import android.support.annotation.NonNull;

import com.lucaslz.weather.data.ProvincesDataSource;
import com.lucaslz.weather.domain.Province;

import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
public class ProvincesLocalDataSource implements ProvincesDataSource {

    private static volatile ProvincesLocalDataSource INSTANCE;

    private ProvincesDao mProvincesDao;

    private ProvincesLocalDataSource(@NonNull ProvincesDao provincesDao) {
        mProvincesDao = provincesDao;
    }

    public static ProvincesLocalDataSource getInstance(@NonNull ProvincesDao provincesDao) {
        if (INSTANCE == null) {
            synchronized (ProvincesLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ProvincesLocalDataSource(provincesDao);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void findProvinces(@NonNull LoadProvincesCallback callback) {
        final List<Province> provinces = mProvincesDao.findProvinces();
        if (provinces.isEmpty()) {
            callback.onDataNotAvailable();
        } else {
            callback.onProvincesLoaded(provinces);
        }
    }

    @Override
    public void deleteAllProvinces() {
        mProvincesDao.deleteProvinces();

    }

    @Override
    public void saveProvince(Province province) {
        mProvincesDao.insertProvince(province);
    }
}
