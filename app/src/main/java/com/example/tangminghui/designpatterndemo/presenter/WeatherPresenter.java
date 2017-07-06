package com.example.tangminghui.designpatterndemo.presenter;

import android.util.Log;
import android.widget.Toast;

import com.example.tangminghui.designpatterndemo.activity.MvcDesignActivity;
import com.example.tangminghui.designpatterndemo.app.DesignPatternConfig;
import com.example.tangminghui.designpatterndemo.entity.Result;
import com.example.tangminghui.designpatterndemo.entity.ResultEntity;
import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherRxService;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherView;
import com.example.tangminghui.designpatterndemo.utils.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by tangminghui on 2017/7/1.
 */

public class WeatherPresenter extends MvpBasePresenter<WeatherView> {

    private static final String TAG = "WeatherPresenter";

    public void get(String key,String city,String province) {
        getView().onLoadWeatherStart();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DesignPatternConfig.MOB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpUtils.getClient())
                .build();
        WeatherRxService weatherService = retrofit.create(WeatherRxService.class);
        weatherService.getWeather(key,city,province)
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Result<ResultEntity>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,e.toString());
                    }

                    @Override
                    public void onNext(Result<ResultEntity> resultEntityResult) {
                        if (!"200".equals(resultEntityResult.getRetCode())){
//                            Toast.makeText(MvcDesignActivity.this,result.getMsg(),Toast.LENGTH_LONG).show();
                        }else {
                            List<ResultEntity> resultEntities = resultEntityResult.getResult();
                            List<WeatherEntity> weatherList = resultEntities.get(0).getFuture();
                            Log.i(TAG, "weather:" + weatherList.toString());
                            WeatherView weatherView = getView();
                            if (weatherView != null){
                                weatherView.onLoadWeatherComplete(weatherList);
                            }

                        }
                    }
                });
    }
}
