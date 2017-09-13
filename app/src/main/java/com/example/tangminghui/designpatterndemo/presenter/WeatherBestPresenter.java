package com.example.tangminghui.designpatterndemo.presenter;

import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;
import com.example.tangminghui.designpatterndemo.entity.WeatherMvvm;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherView;
import com.example.tangminghui.designpatterndemo.model.IWeatherModel;
import com.example.tangminghui.designpatterndemo.model.WeatherBestModel;

import java.util.List;

/**
 * Created by hui on 2017/9/13.
 */

public class WeatherBestPresenter implements BasePresenter {

    private WeatherView weatherView;
    private WeatherBestModel weatherBestModel;

    public WeatherBestPresenter(WeatherView weatherView) {
        this.weatherView = weatherView;
        weatherBestModel = new WeatherBestModel();
    }

    @Override
    public void getWeathers() {
        weatherView.onLoadWeatherStart();
        if ("".equals(weatherView.getProvince()) || "".equals(weatherView.getCity())){
            weatherView.sendErrorMessage();
        }else {
            weatherBestModel.loadWeathers(weatherView.getProvince(), weatherView.getCity(), new IWeatherModel.onLoadListener<WeatherMvvm>() {
                @Override
                public void onError() {
                    weatherView.onLoadWeatherError();
                }

                @Override
                public void onSuccess(List<WeatherMvvm> weathers) {
                    if (weatherView != null) {
                        weatherView.onLoadWeatherComplete(weathers);
                    }
                }
            });
        }
    }
}
