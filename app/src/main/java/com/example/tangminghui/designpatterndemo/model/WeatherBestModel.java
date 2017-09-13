package com.example.tangminghui.designpatterndemo.model;

import android.util.Log;

import com.example.tangminghui.designpatterndemo.app.DesignPatternConfig;
import com.example.tangminghui.designpatterndemo.app.MainApplication;
import com.example.tangminghui.designpatterndemo.entity.Result;
import com.example.tangminghui.designpatterndemo.entity.ResultEntity;
import com.example.tangminghui.designpatterndemo.entity.ResultMvvm;
import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;
import com.example.tangminghui.designpatterndemo.entity.WeatherMvvm;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherDBService;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherRxService;
import com.example.tangminghui.designpatterndemo.utils.OkHttpUtils;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hui on 2017/9/13.
 */

public class WeatherBestModel implements IWeatherModel<WeatherMvvm> {

    private static final String TAG = "WeatherBestModel";

    @Override
    public void loadWeathers(String province, String city, final onLoadListener<WeatherMvvm> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DesignPatternConfig.MOB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpUtils.getClient(MainApplication.getInstance()))
                .build();
        WeatherDBService weatherDBService = retrofit.create(WeatherDBService.class);
        weatherDBService.getWeather(DesignPatternConfig.MOBAPI_KEY,city,province)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<ResultMvvm>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,e.toString());
                        listener.onError();
                    }
                    @Override
                    public void onNext(Result<ResultMvvm> resultMvvmResult) {
                        if (!"200".equals(resultMvvmResult.getRetCode())){
                            Log.i(TAG,resultMvvmResult.getMsg());
                            listener.onError();
                        }else {
                            List<ResultMvvm> resultMvvms = resultMvvmResult.getResult();
                            List<WeatherMvvm> weatherMvvms = resultMvvms.get(0).getFuture();
                            Log.i(TAG, "weather:" + weatherMvvms.toString());
                            listener.onSuccess(weatherMvvms);

                        }
                    }
                });
    }
}
