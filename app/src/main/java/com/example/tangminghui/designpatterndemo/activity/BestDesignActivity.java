package com.example.tangminghui.designpatterndemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.tangminghui.designpatterndemo.R;
import com.example.tangminghui.designpatterndemo.adapter.WeatherMvvmAdapter;
import com.example.tangminghui.designpatterndemo.databinding.ActivityBestBinding;
import com.example.tangminghui.designpatterndemo.entity.Weather;
import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;
import com.example.tangminghui.designpatterndemo.entity.WeatherMvvm;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherView;
import com.example.tangminghui.designpatterndemo.presenter.WeatherBestPresenter;
import com.example.tangminghui.designpatterndemo.presenter.WeathersPresenter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangminghui on 2017/6/26.
 */

public class BestDesignActivity extends Activity implements WeatherView<WeatherMvvm> {

    private static final String TAG = "BestDesignActivity";

    private ProgressDialog progressDialog;

    private ActivityBestBinding binding;

    List<WeatherMvvm> weatherEntities = new ArrayList<>();

    private WeathersPresenter presenter = new WeathersPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_best);
        binding.setWeather(new Weather("请输入省","请输入市"));
        binding.setPresenter(new WeatherBestPresenter(this));
        binding.ryBestWeather.setLayoutManager(new LinearLayoutManager(this));
        binding.ryBestWeather.setAdapter(new WeatherMvvmAdapter(weatherEntities));
    }



    @Override
    public String getProvince() {
        return binding.etBestProvince.getText().toString();
    }

    @Override
    public String getCity() {
        return binding.etBestCity.getText().toString();
    }

    @Override
    public void sendErrorMessage() {
        dismissProgressDialog();
        Toast.makeText(this,"信息输入错误！！！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadWeatherStart() {
        showProgressDialog();
    }

    @Override
    public void onLoadWeatherComplete(List<WeatherMvvm> weatherMvvmList) {
        dismissProgressDialog();
        for (WeatherMvvm weatherMvvm:weatherMvvmList) {
            weatherEntities.add(weatherMvvm);
        }
        Logger.d(weatherMvvmList);
    }

    @Override
    public void onLoadWeatherError() {
        dismissProgressDialog();
        Toast.makeText(this,"数据加载失败",Toast.LENGTH_SHORT).show();
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage("正在加载中");
        progressDialog.show();
    }

    private void dismissProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.dismiss();
    }
}
