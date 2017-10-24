package com.lucaslz.weather.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/23.
 */
public final class HttpUtil {

    public static void sendOKHttpRequest(String url, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

}
