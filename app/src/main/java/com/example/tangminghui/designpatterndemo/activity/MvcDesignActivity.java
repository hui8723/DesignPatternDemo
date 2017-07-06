package com.example.tangminghui.designpatterndemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tangminghui.designpatterndemo.R;
import com.example.tangminghui.designpatterndemo.adapter.WeatherAdapter;
import com.example.tangminghui.designpatterndemo.app.DesignPatternConfig;
import com.example.tangminghui.designpatterndemo.entity.Result;
import com.example.tangminghui.designpatterndemo.entity.ResultEntity;
import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;
import com.example.tangminghui.designpatterndemo.interfaces.WeatherService;
import com.example.tangminghui.designpatterndemo.utils.OkHttpUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tangminghui on 2017/6/26.
 */

public class MvcDesignActivity extends Activity {

    private static final String TAG = "MvcDesignActivity";

    private EditText etProvince,etCity;
    private Button btnSubmit;
    private RecyclerView ry_weather;
    private WeatherAdapter adapter;
    private List<WeatherEntity> weatherEntities = new ArrayList<WeatherEntity>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        initData();

        initView();

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

    private void initView() {
        etProvince = (EditText) findViewById(R.id.et_province);
        etCity = (EditText) findViewById(R.id.et_city);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        ry_weather = (RecyclerView) findViewById(R.id.ry_weather);
        adapter = new WeatherAdapter(MvcDesignActivity.this,weatherEntities);
        ry_weather.setLayoutManager(new LinearLayoutManager(MvcDesignActivity.this));
        ry_weather.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(etProvince.getText().toString()) || "".equals(etCity.getText().toString())){
                    Toast.makeText(MvcDesignActivity.this,"The Province or City is not null.",Toast.LENGTH_LONG).show();
                }else {
                    Log.i(TAG, "Province:" + etProvince.getText().toString() + ",City:" + etCity.getText().toString());
                    getWeatherFromCity(etProvince.getText().toString(),etCity.getText().toString());
                }
            }
        });

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
                .client(OkHttpUtils.getClient())
                .build();
        final WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<Result<ResultEntity>> call = weatherService.getWeather(DesignPatternConfig.MOBAPI_KEY,city,province);
        call.enqueue(new Callback<Result<ResultEntity>>() {
            @Override
            public void onResponse(Call<Result<ResultEntity>> call, Response<Result<ResultEntity>> response) {
                Result<ResultEntity> result = response.body();
                 if (!"200".equals(result.getRetCode())){
                    Toast.makeText(MvcDesignActivity.this,result.getMsg(),Toast.LENGTH_LONG).show();
                }else {
                    List<ResultEntity> resultEntities = result.getResult();
                    List<WeatherEntity> weatherList = resultEntities.get(0).getFuture();
                    Log.i(TAG,"weather:" + weatherList.toString());
                    for (WeatherEntity weatherEntity:weatherList){
                        weatherEntities.add(weatherEntity);
                    }
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<Result<ResultEntity>> call, Throwable t) {
                Log.i(TAG,"Failure:" + t.toString());
            }
        });
    }
}
