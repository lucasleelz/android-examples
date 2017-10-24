package com.lucaslz.weather.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lucaslz.weather.domain.Province;

import java.util.List;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/24.
 */
public final class Utility {

    public static boolean handleProvinceResponse(String response) {
        if (TextUtils.isEmpty(response)) {
            return false;
        }
        List<Province> provinces = new Gson().fromJson(response, new TypeToken<List<Province>>() {
        }.getType());
        return true;
    }
}
