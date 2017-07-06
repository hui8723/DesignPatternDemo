package com.example.tangminghui.designpatterndemo.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tangminghui.designpatterndemo.R;
import com.example.tangminghui.designpatterndemo.adapter.WeatherAdapter;
import com.example.tangminghui.designpatterndemo.app.DesignPatternConfig;
import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherView;
import com.example.tangminghui.designpatterndemo.presenter.WeatherPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangminghui on 2017/6/26.
 */

public class MvpDesignActivity extends MvpActivity<WeatherView,WeatherPresenter> implements WeatherView{

    private static final String TAG = "MvpDesignActivity";

    private ProgressDialog progressDialog;
    private EditText etProvince,etCity;
    private Button btnSubmit;
    private RecyclerView ry_weather;
    private WeatherAdapter adapter;
    private List<WeatherEntity> weatherEntities = new ArrayList<WeatherEntity>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        initData();
        initView();

    }

    private void initView() {
        etProvince = (EditText) findViewById(R.id.et_province);
        etCity = (EditText) findViewById(R.id.et_city);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        ry_weather = (RecyclerView) findViewById(R.id.ry_weather);
        adapter = new WeatherAdapter(MvpDesignActivity.this,weatherEntities);
        ry_weather.setLayoutManager(new LinearLayoutManager(MvpDesignActivity.this));
        ry_weather.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etProvince.getText().toString()) || "".equals(etCity.getText().toString())){
                    Toast.makeText(MvpDesignActivity.this,"The Province or City is not null.",Toast.LENGTH_LONG).show();
                }else {
                    Log.i(TAG, "Province:" + etProvince.getText().toString() + ",City:" + etCity.getText().toString());
                    getPresenter().get(DesignPatternConfig.MOBAPI_KEY,etCity.getText().toString(),etProvince.getText().toString());
                }
            }
        });
    }

    private void initData() {
        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setDate("aaa");
        weatherEntity.setDayTime("aaaa");
        weatherEntity.setTemperature("bbb");
        weatherEntity.setWind("bbbb");
        weatherEntity.setWeek("ccc");
        weatherEntity.setNight("cccc");
        weatherEntities.add(0,weatherEntity);
    }

    @NonNull
    @Override
    public WeatherPresenter createPresenter() {
        return new WeatherPresenter();
    }

    @Override
    public void onLoadWeatherStart() {
        showProgressDialog();

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

    @Override
    public void onLoadWeatherComplete(List<WeatherEntity> weatherList) {
        dismissProgressDialog();
        for (WeatherEntity weatherEntity:weatherList){
            weatherEntities.add(weatherEntity);
        }
        adapter.notifyDataSetChanged();
    }
}
