package com.example.tangminghui.designpatterndemo.presenter;

import android.util.Log;
import android.view.View;

import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherView;
import com.example.tangminghui.designpatterndemo.model.IWeatherModel;
import com.example.tangminghui.designpatterndemo.model.WeatherModel;

import java.util.List;

/**
 * Created by hui on 2017/9/12.
 */

public class WeathersPresenter implements BasePresenter{

    private WeatherView weatherView;
    private WeatherModel weatherModel;

    public WeathersPresenter(WeatherView weatherView) {
        this.weatherView = weatherView;
        weatherModel = new WeatherModel();
    }

    @Override
    public void getWeathers() {
        weatherView.onLoadWeatherStart();
        if ("".equals(weatherView.getProvince()) || "".equals(weatherView.getCity())){
            weatherView.sendErrorMessage();
        }else {
            weatherModel.loadWeathers(weatherView.getProvince(), weatherView.getCity(), new IWeatherModel.onLoadListener<WeatherEntity>() {
                @Override
                public void onError() {
                    weatherView.onLoadWeatherError();
                }

                @Override
                public void onSuccess(List<WeatherEntity> weathers) {
                    if (weatherView != null) {
                        weatherView.onLoadWeatherComplete(weathers);
                    }
                }
            });
        }
    }


}
