package com.lucaslz.weather.data.remote;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lucaslz.weather.data.ProvincesDataSource;
import com.lucaslz.weather.data.local.ProvincesDao;
import com.lucaslz.weather.domain.Province;
import com.lucaslz.weather.utils.HttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
public class ProvincesRemoteDataSource implements ProvincesDataSource {

    public static final String TAG = ProvincesRemoteDataSource.class.getSimpleName();

    private static volatile ProvincesRemoteDataSource INSTANCE;

    private ProvincesDao mProvincesDao;

    private ProvincesRemoteDataSource() {
    }

    public static ProvincesRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (ProvincesRemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ProvincesRemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void findProvinces(@NonNull LoadProvincesCallback callback) {
        HttpUtil.sendOKHttpRequest("https://m.baidu.com/", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onDataNotAvailable();
                Log.e(TAG, e.getLocalizedMessage());
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseJSON = response.body().toString();
                List<Province> provinces = new Gson().fromJson(responseJSON, new TypeToken<List<Province>>() {
                }.getType());
                if (TextUtils.isEmpty(responseJSON) || provinces.isEmpty()) {
                    callback.onDataNotAvailable();
                    return;
                }
                callback.onProvincesLoaded(provinces);
            }
        });
    }

    // TODO: delete remove provinces.
    @Override
    public void deleteAllProvinces() {

    }

    // TODO: save remove provinces.
    @Override
    public void saveProvince(Province province) {

    }
}
