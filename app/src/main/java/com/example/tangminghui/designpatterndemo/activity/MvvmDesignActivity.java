package com.example.tangminghui.designpatterndemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tangminghui.designpatterndemo.R;
import com.example.tangminghui.designpatterndemo.adapter.WeatherMvvmAdapter;
import com.example.tangminghui.designpatterndemo.databinding.ActivityMvvmBinding;
import com.example.tangminghui.designpatterndemo.entity.Weather;
import com.example.tangminghui.designpatterndemo.entity.WeatherMvvm;
import com.example.tangminghui.designpatterndemo.model.IWeatherModel;
import com.example.tangminghui.designpatterndemo.model.WeatherBestModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangminghui on 2017/6/26.
 */

public class MvvmDesignActivity extends Activity{

    private static final String TAG = "MvvmDesignActivity";
    private ActivityMvvmBinding binding;
    private ProgressDialog progressDialog;
    private WeatherMvvmAdapter adapter;
    private List<WeatherMvvm> weatherMvvms = new ArrayList<>();
    private Weather weather;
    private WeatherBestModel weatherBestModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);

        initData();

    }

    private void initData() {
        weather = new Weather("请输入省","请输入市");
        binding.setWeather(weather);
        adapter = new WeatherMvvmAdapter(weatherMvvms);
        binding.ryMvvmWeather.setAdapter(adapter);
        binding.ryMvvmWeather.setLayoutManager(new LinearLayoutManager(this));
    }

    public void weatherFromBtn(View view){
        weatherBestModel = new WeatherBestModel();
        Log.i(TAG,"weather:" + weather.getProvince() );
        if ("请输入省".equals(weather.getProvince()) || "请输入市".equals(weather.getCity())){
            Toast.makeText(MvvmDesignActivity.this,"数据不能为空",Toast.LENGTH_SHORT).show();
        }else {
            showProgressDialog();
            Log.i(TAG,weather.getProvince());
            weatherBestModel.loadWeathers(weather.getProvince(), weather.getCity(), new IWeatherModel.onLoadListener<WeatherMvvm>() {
                @Override
                public void onError() {
                    dismissProgressDialog();
                    Toast.makeText(MvvmDesignActivity.this,"数据加载失败",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSuccess(List<WeatherMvvm> weathers) {
                    dismissProgressDialog();
                    for (WeatherMvvm weatherEntity:weathers){
                        weatherMvvms.add(weatherEntity);
                    }
                    adapter.notifyDataSetChanged();
                }
            });
        }
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
