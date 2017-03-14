package com.example.congbai.fundweather;

import android.content.Context;

import com.example.congbai.fundweather.model.network.NetWork;
import com.example.congbai.fundweather.model.repository.ChooseAreaRepository;
import com.example.congbai.fundweather.model.repository.ShowWeatherRepository;
import com.example.congbai.fundweather.util.LocationUtil;
import com.example.congbai.fundweather.util.RealmHelper;
import com.example.congbai.fundweather.util.ToastUtil;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by fundmarkhua on 2017/2/28
 * Email:57525101@qq.com
 * 和 ApplicationModule配合担负BaseComponent的角色
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    ChooseAreaRepository getChooseAreaRepository();

    ShowWeatherRepository getWeatherRepository();

    Context getContext();

    ToastUtil getToastUtil();

    LocationUtil getLocationUtil();
/**
 RealmHelper getRealmHelper();

 NetWork getNetWork();
 **/
}
