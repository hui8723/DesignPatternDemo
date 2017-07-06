package com.example.tangminghui.designpatterndemo.entity;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.tangminghui.designpatterndemo.BR;

import java.util.List;

/**
 * Created by tangminghui on 2017/7/4.
 */

public class Weather extends BaseObservable{
    private String province;
    private String city;

    public Weather(String province,String city){
        this.province = province;
        this.city = city;
    }

    @Bindable
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
        notifyPropertyChanged(BR.province);
    }

    @Bindable
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        notifyPropertyChanged(BR.city);
    }

}
