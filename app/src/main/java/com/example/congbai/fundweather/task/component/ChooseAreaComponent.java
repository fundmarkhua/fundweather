package com.example.congbai.fundweather.task.component;

import com.example.congbai.fundweather.ApplicationComponent;
import com.example.congbai.fundweather.task.activity.ChooseAreaActivity;
import com.example.congbai.fundweather.task.module.ChooseAreaModule;
import com.example.congbai.fundweather.util.FragmentScoped;

import dagger.Component;

/**
 * Created by fundmarkhua on 2017/2/25
 * Email:57525101@qq.com
 */
@FragmentScoped
@Component(dependencies = ApplicationComponent.class, modules = ChooseAreaModule.class)

public interface ChooseAreaComponent {
    void inject(ChooseAreaActivity chooseAreaActivity);
}
