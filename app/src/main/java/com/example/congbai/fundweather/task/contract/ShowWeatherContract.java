package com.example.congbai.fundweather.task.contract;

import com.example.congbai.fundweather.BasePresenter;
import com.example.congbai.fundweather.BaseView;
import com.example.congbai.fundweather.model.network.gson.Weather;


/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 */

public interface ShowWeatherContract {
    interface View extends BaseView<ShowWeatherContract.Presenter> {

        void showWeatherData(Weather weatherData);

        void showBackgroundImage(String imgUrl);

        void showExceptionMessage(String message);

        void showRefreshStatus();

    }

    interface Presenter extends BasePresenter {

        void getWeatherDataById(String weatherId);

        void getWeatherDataByString(String weatherInfo);

        void loadBingPic();
    }
}
