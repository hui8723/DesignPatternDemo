package com.example.tangminghui.designpatterndemo.model;

import android.util.Log;

import com.example.tangminghui.designpatterndemo.app.DesignPatternConfig;
import com.example.tangminghui.designpatterndemo.app.MainApplication;
import com.example.tangminghui.designpatterndemo.entity.Result;
import com.example.tangminghui.designpatterndemo.entity.ResultEntity;
import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;
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
 * Created by hui on 2017/9/12.
 */

public class WeatherModel implements IWeatherModel<WeatherEntity>{

    private static final String TAG = "WeatherModel";

    @Override
    public void loadWeathers(String province, String city, final onLoadListener<WeatherEntity> listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DesignPatternConfig.MOB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpUtils.getClient(MainApplication.getInstance()))
                .build();
        WeatherRxService weatherService = retrofit.create(WeatherRxService.class);
        weatherService.getWeather(DesignPatternConfig.MOBAPI_KEY,city,province)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<ResultEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,e.toString());
                        listener.onError();
                    }

                    @Override
                    public void onNext(Result<ResultEntity> resultEntityResult) {
                        if (!"200".equals(resultEntityResult.getRetCode())){
                            Log.i(TAG,resultEntityResult.getMsg());
                            listener.onError();
                        }else {
                            List<ResultEntity> resultEntities = resultEntityResult.getResult();
                            List<WeatherEntity> weatherList = resultEntities.get(0).getFuture();
                            Log.i(TAG, "weather:" + weatherList.toString());
                            listener.onSuccess(weatherList);

                        }
                    }
                });
    }
}
