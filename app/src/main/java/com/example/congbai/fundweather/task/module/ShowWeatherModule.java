package com.example.congbai.fundweather.task.module;

import com.example.congbai.fundweather.task.contract.ShowWeatherContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 */
@Module
public class ShowWeatherModule {
    private final ShowWeatherContract.View mView;

    public ShowWeatherModule(ShowWeatherContract.View view) {
        mView = view;
    }

    @Provides
    ShowWeatherContract.View provideShowWeatherContractView() {
        return mView;
    }
}
