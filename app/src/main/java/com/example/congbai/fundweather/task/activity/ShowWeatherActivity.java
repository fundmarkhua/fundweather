package com.example.congbai.fundweather.task.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.congbai.fundweather.BaseActivity;
import com.example.congbai.fundweather.MyApplication;
import com.example.congbai.fundweather.R;
import com.example.congbai.fundweather.task.component.DaggerShowWeatherComponent;
import com.example.congbai.fundweather.task.fragment.ShowWeatherFragment;
import com.example.congbai.fundweather.task.module.ShowWeatherModule;
import com.example.congbai.fundweather.task.presenter.ChooseAreaPresenter;
import com.example.congbai.fundweather.task.presenter.ShowWeatherPresenter;
import com.example.congbai.fundweather.util.ActivityUtile;

import javax.inject.Inject;

/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 */

public class ShowWeatherActivity extends BaseActivity {
    @Inject
    ShowWeatherPresenter mShowWeatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_weather);
        ShowWeatherFragment showWeatherFragment = (ShowWeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.weatherFrame);
        if (showWeatherFragment == null) {
            showWeatherFragment = ShowWeatherFragment.newInstance();
            ActivityUtile.addFragmentToActivity(getSupportFragmentManager(),
                    showWeatherFragment, R.id.weatherFrame);
        }

        //create presenter
        DaggerShowWeatherComponent.builder()
                .showWeatherModule(new ShowWeatherModule(showWeatherFragment))
                .applicationComponent(((MyApplication) getApplication()).getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
