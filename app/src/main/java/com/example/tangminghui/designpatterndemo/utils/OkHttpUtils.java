package com.example.tangminghui.designpatterndemo.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by tangminghui on 2017/6/28.
 */

public class OkHttpUtils {

//    获取自定义OKHttpClient
    public static OkHttpClient getClient(Context context){

        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger(){
            @Override
            public void log(String message) {
                Log.i("OkHttp","msg=" + message);
            }
        }
        );
        interceptor.setLevel(level);
        Cache cache = new Cache(context.getCacheDir(),10 * 1024 * 1024);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .writeTimeout(20,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(getCacheInterceptor(context)).cache(cache)
                .addInterceptor(getCacheInterceptor(context));
        return builder.build();
    }

    @SuppressWarnings("unused")
    public static Interceptor getRequestHeader() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder builder = originalRequest.newBuilder();
                builder.header("appId","1");
                builder.header("appKey","aaaaaa");
                Request.Builder requestBuilder = builder.method(originalRequest.method(),originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    @SuppressWarnings("unused")
    public static Interceptor commonParamsInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl httpUrl = originalRequest.url().newBuilder()
                        .addQueryParameter("platform","android")
                        .addQueryParameter("version","1.0.0").build();
                Request request = originalRequest.newBuilder().url(httpUrl).build();
                return chain.proceed(request);
            }
        };
    }

    public static Interceptor getCacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                if (! NetworkUtils.isNetworkConnected(context)){
                    originalRequest = originalRequest.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                }
                Response response = chain.proceed(originalRequest);
                if (NetworkUtils.isNetworkConnected(context)) {
                    String cacheContril = originalRequest.cacheControl().toString();
                    return response.newBuilder().header("Cache-Control",cacheContril)
                            .removeHeader("Pragma")
                            .build();
                }else {
                    return response.newBuilder().header("Cache-Control","public,only-if-cached,max-stale-360000")
                            .removeHeader("Pragma")
                            .build();
                }
            }
        };
    }
}
