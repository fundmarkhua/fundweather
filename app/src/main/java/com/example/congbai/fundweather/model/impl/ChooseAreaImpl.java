package com.example.congbai.fundweather.model.impl;

import com.baidu.location.BDLocation;
import com.example.congbai.fundweather.model.entity.Province;
import com.example.congbai.fundweather.model.network.gson.AreaData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by fundmarkhua on 2017/2/27
 * Email:57525101@qq.com
 */

public interface ChooseAreaImpl {
    String getCityTest();

    List<AreaData> getAreaData(String areaType, int areaId);

    List<AreaData> getProvinceLocal();

    List<AreaData> getCityLocal(int provinceId);

    List<AreaData> getCountyLocal(int cityId);

    AreaData getWeatherCode(String cityName);

    String getLocationCity();

    int getProvinceId(int cityId);

    boolean saveProvince(List<AreaData> provinceList);

    boolean saveCity(List<AreaData> cityList, int provinceId);

    boolean saveCounty(List<AreaData> countyList, int cityId);
}
