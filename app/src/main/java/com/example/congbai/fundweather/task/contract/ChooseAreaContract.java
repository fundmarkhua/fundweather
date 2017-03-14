package com.example.congbai.fundweather.task.contract;

import com.example.congbai.fundweather.BasePresenter;
import com.example.congbai.fundweather.BaseView;
import com.example.congbai.fundweather.model.network.gson.AreaData;

import java.util.List;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 * This specifies the contract between the view and the presenter.
 */

public interface ChooseAreaContract {
    interface View extends BaseView<Presenter> {
        void toastMessage(String message);

        void showProvince(List<AreaData> areaDataList);

        void showCity(List<AreaData> areaDataList);

        void showCounty(List<AreaData> areaDataList);

        void showProgressDialog();

        void closeProgressDialog();
    }

    interface Presenter extends BasePresenter {

        void getProvinceData();

        void getCityData(int provinceId,String stemFrom);

        void getCountyData(int cityId);

        void getWeatherCode(String cityName);


    }
}
