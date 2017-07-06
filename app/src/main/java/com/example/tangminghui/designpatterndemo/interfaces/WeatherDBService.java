package com.example.tangminghui.designpatterndemo.interfaces;

import com.example.tangminghui.designpatterndemo.entity.Result;
import com.example.tangminghui.designpatterndemo.entity.ResultEntity;
import com.example.tangminghui.designpatterndemo.entity.ResultMvvm;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by tangminghui on 2017/7/5.
 */

public interface WeatherDBService {

    @GET("query")
    Observable<Result<ResultMvvm>> getWeather(@Query("key") String key, @Query("city") String city, @Query("province") String province);
}
