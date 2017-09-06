package com.example.tangminghui.designpatterndemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.tangminghui.designpatterndemo.R;
import com.example.tangminghui.designpatterndemo.adapter.WeatherMvvmAdapter;
import com.example.tangminghui.designpatterndemo.app.DesignPatternConfig;
import com.example.tangminghui.designpatterndemo.app.MainApplication;
import com.example.tangminghui.designpatterndemo.databinding.ActivityMvvmBinding;
import com.example.tangminghui.designpatterndemo.entity.Result;
import com.example.tangminghui.designpatterndemo.entity.ResultMvvm;
import com.example.tangminghui.designpatterndemo.entity.Weather;
import com.example.tangminghui.designpatterndemo.entity.WeatherMvvm;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherDBService;
import com.example.tangminghui.designpatterndemo.utils.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
        Log.i(TAG,"weather:" + weather.getProvince() );
        if ("请输入省".equals(weather.getProvince()) || "请输入市".equals(weather.getCity())){
            Log.i(TAG,"数据不能为空！");
        }else {
            showProgressDialog();
            Log.i(TAG,weather.getProvince());
            getWeatherFromCity(weather.getProvince(),weather.getCity());
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

    /**
     * 根据城市名查询近10日天气
     * @param province
     * @param city
     */
    private void getWeatherFromCity(String province, String city) {
//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh-mm-ss").create();
        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DesignPatternConfig.MOB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(OkHttpUtils.getClient(MainApplication.getInstance()))
                .build();
        final WeatherDBService weatherDBService = retrofit.create(WeatherDBService.class);
        weatherDBService.getWeather(DesignPatternConfig.MOBAPI_KEY,city,province)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result<ResultMvvm>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dismissProgressDialog();
                        Log.i(TAG,e.toString());
                    }
                    @Override
                    public void onNext(Result<ResultMvvm> resultMvvmResult) {
                        dismissProgressDialog();
                        if (!"200".equals(resultMvvmResult.getRetCode())){

                        }else {
                            List<ResultMvvm> weatherDBServices = resultMvvmResult.getResult();
                            List<WeatherMvvm> weatherMvvmList = weatherDBServices.get(0).getFuture();
                            Log.i(TAG, "weather:" + weatherMvvms.toString());
                            for (WeatherMvvm weatherEntity:weatherMvvmList){
                                weatherMvvms.add(weatherEntity);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
