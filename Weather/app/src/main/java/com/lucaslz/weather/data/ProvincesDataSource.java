package com.lucaslz.weather.data;

import android.support.annotation.NonNull;

import com.lucaslz.weather.domain.Province;

import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
public interface ProvincesDataSource {

    interface LoadProvincesCallback {
        void onProvincesLoaded(List<Province> provinces);

        void onDataNotAvailable();
    }

    void findProvinces(@NonNull LoadProvincesCallback callback);

    void deleteAllProvinces();

    void saveProvince(Province province);
}
