package com.lucaslz.weather.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lucas lee <llzqingdao2012gmail.com> on 2017/10/23.
 */
public final class HttpUtil {

    public static void sendOKHttpRequest(String url, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request.Builder requestBuilder = new Request.Builder().url("http://www.baidu.com");
        //可以省略，默认是GET请求
        requestBuilder.method("GET", null);
        Request request = requestBuilder.build();
        Call mcall = client.newCall(request);
        mcall.enqueue(callback);
//        OkHttpClient client = new OkHttpClient.Builder()
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .readTimeout(20, TimeUnit.SECONDS)
//                .build();
//        Request request = new Request.Builder()
//                .addHeader("Connection", "close")
//                .url(url)
//                .get()
//                .build();
//        client.newCall(request).enqueue(callback);
    }

}
