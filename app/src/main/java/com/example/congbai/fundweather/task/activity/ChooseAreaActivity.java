package com.example.congbai.fundweather.task.activity;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;

import com.example.congbai.fundweather.BaseActivity;
import com.example.congbai.fundweather.MyApplication;
import com.example.congbai.fundweather.R;
import com.example.congbai.fundweather.task.component.DaggerChooseAreaComponent;
import com.example.congbai.fundweather.task.fragment.ChooseAreaFragment;
import com.example.congbai.fundweather.task.module.ChooseAreaModule;
import com.example.congbai.fundweather.task.presenter.ChooseAreaPresenter;
import com.example.congbai.fundweather.util.ActivityUtile;

import javax.inject.Inject;

public class ChooseAreaActivity extends BaseActivity {

    @Inject
    ChooseAreaPresenter mChooseAreaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area);

        ChooseAreaFragment chooseAreaFragment = (ChooseAreaFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (chooseAreaFragment == null) {
            chooseAreaFragment = ChooseAreaFragment.newInstance();
            ActivityUtile.addFragmentToActivity(getSupportFragmentManager(),
                    chooseAreaFragment, R.id.contentFrame);
        }
        //create presenter
        DaggerChooseAreaComponent.builder()
                .chooseAreaModule(new ChooseAreaModule(chooseAreaFragment))
                .applicationComponent(((MyApplication)getApplication()).getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
