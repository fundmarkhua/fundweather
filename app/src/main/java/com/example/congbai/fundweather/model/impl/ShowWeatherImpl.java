package com.example.congbai.fundweather.model.impl;

import com.example.congbai.fundweather.model.network.gson.Weather;

import io.reactivex.Observable;

/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 */

public interface ShowWeatherImpl {
    Observable<Weather> getWeatherData(String weatherId);

    Observable<Weather> getWeatherDataLocal(String weatherInfo);

    Observable<String> getBingPicUrl();

    String getBingPicLocal();
}
