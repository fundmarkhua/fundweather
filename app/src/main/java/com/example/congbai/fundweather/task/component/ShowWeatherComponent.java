package com.example.congbai.fundweather.task.component;

import com.example.congbai.fundweather.ApplicationComponent;
import com.example.congbai.fundweather.task.activity.ShowWeatherActivity;
import com.example.congbai.fundweather.task.module.ShowWeatherModule;
import com.example.congbai.fundweather.util.FragmentScoped;

import dagger.Component;

/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 */
@FragmentScoped
@Component(dependencies = ApplicationComponent.class, modules = ShowWeatherModule.class)
public interface ShowWeatherComponent {
    void inject(ShowWeatherActivity showWeatherActivity);
}
