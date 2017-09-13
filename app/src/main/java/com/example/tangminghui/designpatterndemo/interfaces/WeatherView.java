package com.example.tangminghui.designpatterndemo.interfaces;

import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

/**
 * Created by tangminghui on 2017/6/30.
 */

public interface WeatherView<T> extends MvpView {

    String getProvince();

    String getCity();

    void sendErrorMessage();

//    开始加载天气数据
    void onLoadWeatherStart();

//    加载天气数据完毕
    void onLoadWeatherComplete(List<T> weatherEntities);

    void onLoadWeatherError();

}
