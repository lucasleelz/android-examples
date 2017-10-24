package com.lucaslz.weather.data;

import android.support.annotation.NonNull;

import com.lucaslz.weather.domain.Province;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
public class ProvincesRepository implements ProvincesDataSource {

    private static ProvincesRepository INSTANCE = null;
    private final ProvincesDataSource mProvincesLocalDataSource;
    private final ProvincesDataSource mProvincesRemoteDataSource;

    Map<Integer, Province> mCachedProvinces;

    private ProvincesRepository(ProvincesDataSource provincesLocalDataSource, ProvincesDataSource provincesRemoteDataSource) {
        mProvincesLocalDataSource = provincesLocalDataSource;
        mProvincesRemoteDataSource = provincesRemoteDataSource;
    }

    public static ProvincesRepository getInstance(ProvincesDataSource provincesLocalDataSource, ProvincesDataSource provincesRemoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ProvincesRepository(provincesLocalDataSource, provincesRemoteDataSource);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void findProvinces(@NonNull LoadProvincesCallback callback) {
        if (mCachedProvinces != null) {
            callback.onProvincesLoaded(new ArrayList<>(mCachedProvinces.values()));
            return;
        }
        mProvincesLocalDataSource.findProvinces(new LoadProvincesCallback() {
            @Override
            public void onProvincesLoaded(List<Province> provinces) {
                refreshCache(provinces);
                callback.onProvincesLoaded(new ArrayList<>(mCachedProvinces.values()));
            }

            @Override
            public void onDataNotAvailable() {
                mProvincesRemoteDataSource.findProvinces(new LoadProvincesCallback() {
                    @Override
                    public void onProvincesLoaded(List<Province> provinces) {
                        refreshCache(provinces);
                        refreshLocalDataSource(provinces);
                        callback.onProvincesLoaded(new ArrayList<>(mCachedProvinces.values()));
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });
            }
        });
    }

    @Override
    public void deleteAllProvinces() {
        mProvincesRemoteDataSource.deleteAllProvinces();
        mProvincesLocalDataSource.deleteAllProvinces();
        if (mCachedProvinces == null) {
            mCachedProvinces = new LinkedHashMap<>();
        }
        mCachedProvinces.clear();
    }

    @Override
    public void saveProvince(Province province) {

    }

    private void refreshCache(List<Province> provinces) {
        if (mCachedProvinces == null) {
            mCachedProvinces = new LinkedHashMap<>();
        }
        mCachedProvinces.clear();
        for (Province province : provinces) {
            mCachedProvinces.put(province.getId(), province);
        }
    }

    private void refreshLocalDataSource(List<Province> provinces) {
        mProvincesLocalDataSource.deleteAllProvinces();
        for (Province province : provinces) {
            mProvincesLocalDataSource.saveProvince(province);
        }
    }
}
