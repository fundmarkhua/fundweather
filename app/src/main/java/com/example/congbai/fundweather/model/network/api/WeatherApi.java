package com.example.congbai.fundweather.model.network.api;

import com.example.congbai.fundweather.model.network.gson.AreaData;
import com.example.congbai.fundweather.model.network.gson.Weather;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by fundmarkhua on 2017/3/8
 * Email:57525101@qq.com
 */

public interface WeatherApi {
    @GET("weather")
    Observable<Weather> getWeatherRx(@Query("cityid") String cityId, @Query("key") String key);

    @GET("weather")
    Call<Weather> getWeather(@Query("cityid") String cityId, @Query("key") String key);

}
