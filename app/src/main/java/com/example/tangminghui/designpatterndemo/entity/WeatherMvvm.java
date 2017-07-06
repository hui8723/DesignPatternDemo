package com.example.tangminghui.designpatterndemo.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.tangminghui.designpatterndemo.BR;

/**
 * Created by tangminghui on 2017/7/5.
 */

public class WeatherMvvm extends BaseObservable{

    private String date;
    private String dayTime;
    private String night;
    private String temperature;
    private String week;
    private String wind;

    @Bindable
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    @Bindable
    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
        notifyPropertyChanged(BR.dayTime);
    }

    @Bindable
    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
        notifyPropertyChanged(BR.night);
    }

    @Bindable
    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
        notifyPropertyChanged(BR.temperature);
    }

    @Bindable
    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
        notifyPropertyChanged(BR.week);
    }

    @Bindable
    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
        notifyPropertyChanged(BR.wind);
    }
}
