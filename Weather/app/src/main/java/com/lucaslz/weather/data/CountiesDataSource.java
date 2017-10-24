package com.lucaslz.weather.data;

import android.support.annotation.NonNull;

import com.lucaslz.weather.domain.County;

import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
public interface CountiesDataSource {

    interface LoadCountiesCallback {
        void onCountiesLoaded(List<County> counties);

        void onDataNotAvailable();
    }

    void findCounties(@NonNull LoadCountiesCallback callback);

}
