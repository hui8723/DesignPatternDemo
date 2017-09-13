package com.example.tangminghui.designpatterndemo.model;


import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;

import java.util.List;

/**
 * Created by hui on 2017/9/12.
 */

public interface IWeatherModel<T> {

    void loadWeathers(String province,String city,onLoadListener<T> listener);

    interface onLoadListener<T> {
        void onError();
        void onSuccess(List<T> weathers);
    }
}
