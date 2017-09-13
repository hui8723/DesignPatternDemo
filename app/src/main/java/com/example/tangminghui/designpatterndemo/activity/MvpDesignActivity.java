package com.example.tangminghui.designpatterndemo.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tangminghui.designpatterndemo.R;
import com.example.tangminghui.designpatterndemo.adapter.WeatherAdapter;
import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherView;
import com.example.tangminghui.designpatterndemo.presenter.WeathersPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tangminghui on 2017/6/26.
 */

public class MvpDesignActivity extends Activity implements WeatherView<WeatherEntity>{

    private static final String TAG = "MvpDesignActivity";

    private ProgressDialog progressDialog;
    private EditText etProvince,etCity;
    private Button btnSubmit;
    private RecyclerView ry_weather;
    private WeatherAdapter adapter;
    private List<WeatherEntity> weatherEntities = new ArrayList<>();
    private WeathersPresenter presenter = new WeathersPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
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
                presenter.getWeathers();

            }
        });
    }



    @Override
    public String getProvince() {
        return etProvince.getText().toString();
    }

    @Override
    public String getCity() {
        return etCity.getText().toString();
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

    @Override
    public void onLoadWeatherError() {
        dismissProgressDialog();
        Toast.makeText(this,"数据加载失败",Toast.LENGTH_SHORT).show();
    }
}
