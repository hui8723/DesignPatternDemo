package com.example.tangminghui.designpatterndemo.interfaces;

import com.example.tangminghui.designpatterndemo.entity.Result;
import com.example.tangminghui.designpatterndemo.entity.ResultEntity;
import com.example.tangminghui.designpatterndemo.entity.WeatherEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by tangminghui on 2017/6/27.
 */

public interface WeatherService {

    @GET("query")
    Call<Result<ResultEntity>> getWeather(@Query("key") String key, @Query("city") String city, @Query("province") String province);

}
