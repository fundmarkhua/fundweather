package com.example.congbai.fundweather.model.network.api;

import com.example.congbai.fundweather.model.entity.Province;
import com.example.congbai.fundweather.model.network.gson.AreaData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by fundmarkhua on 2017/2/28
 * Email:57525101@qq.com
 */

public interface AreaApi {
    @GET("china")
    Observable<List<AreaData>> getProvincesRx();

    @GET("china/{provinceId}")
    Observable<List<AreaData>> getCitiesRx(@Path("provinceId") int provinceId);

    @GET("china/city/{cityId}")
    Observable<List<AreaData>> getCountiesRx(@Path("cityId") int cityId);

    @GET("china")
    Call<List<AreaData>> getProvinces();

    @GET("china/{provinceId}")
    Call<List<AreaData>> getCities(@Path("provinceId") int provinceId);

    @GET("china/city/{cityId}")
    Call<List<AreaData>> getCounties(@Path("cityId") int cityId);

}
