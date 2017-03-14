package com.example.congbai.fundweather.model.network.api;

import com.example.congbai.fundweather.model.entity.Province;
import com.example.congbai.fundweather.model.network.gson.AreaData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by fundmarkhua on 2017/2/28
 * Email:57525101@qq.com
 */

public interface AreaApi {

    @GET("search")
    Call<List<AreaData>> getWeatherCode(@Query("city") String cityId);

    @GET("china")
    Call<List<AreaData>> getProvinces();

    @GET("china/{provinceId}")
    Call<List<AreaData>> getCities(@Path("provinceId") int provinceId);

    @GET("china/city/{cityId}")
    Call<List<AreaData>> getCounties(@Path("cityId") int cityId);

}
