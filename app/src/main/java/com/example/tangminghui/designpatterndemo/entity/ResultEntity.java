package com.example.tangminghui.designpatterndemo.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by tangminghui on 2017/6/27.
 */

public class ResultEntity {

    private String airCondition;
    private String city;
    private String coldIndex;
    private String date;
    private String distrct;
    private String dressingIndex;
    private String exerciseIndex;
    private List<WeatherEntity> future;

    public String getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(String airCondition) {
        this.airCondition = airCondition;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getColdIndex() {
        return coldIndex;
    }

    public void setColdIndex(String coldIndex) {
        this.coldIndex = coldIndex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistrct() {
        return distrct;
    }

    public void setDistrct(String distrct) {
        this.distrct = distrct;
    }

    public String getDressingIndex() {
        return dressingIndex;
    }

    public void setDressingIndex(String dressingIndex) {
        this.dressingIndex = dressingIndex;
    }

    public String getExerciseIndex() {
        return exerciseIndex;
    }

    public void setExerciseIndex(String exerciseIndex) {
        this.exerciseIndex = exerciseIndex;
    }

    public List<WeatherEntity> getFuture() {
        return future;
    }

    public void setFuture(List<WeatherEntity> future) {
        this.future = future;
    }
}
