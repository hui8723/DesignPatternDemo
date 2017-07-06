package com.example.tangminghui.designpatterndemo.utils;

import android.util.Log;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by tangminghui on 2017/6/28.
 */

public class OkHttpUtils {

//    获取自定义OKHttpClient
    public static OkHttpClient getClient(){

        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger(){
            @Override
            public void log(String message) {
                Log.i("OkHttp","msg=" + message);
            }
        }
        );
        interceptor.setLevel(level);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        return builder.build();
    }
}
