package com.example.congbai.fundweather.task.activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.congbai.fundweather.R;
import com.example.congbai.fundweather.task.component.DaggerChooseAreaComponent;
import com.example.congbai.fundweather.task.fragment.ChooseAreaFragment;
import com.example.congbai.fundweather.task.module.ChooseAreaModule;
import com.example.congbai.fundweather.task.presenter.ChooseAreaPresenter;
import com.example.congbai.fundweather.util.ActivityUtile;

import javax.inject.Inject;

public class ChooseAreaActivity extends AppCompatActivity {
    private static final String TAG = "ChooseAreaActivity";
    @Inject
    ChooseAreaPresenter mchooseAreaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_area);
        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        try {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        } catch (Exception e) {
            Log.w(TAG, "onCreate: ", e);
        }

        ChooseAreaFragment chooseAreaFragment = (ChooseAreaFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (chooseAreaFragment == null) {
            chooseAreaFragment = ChooseAreaFragment.newInstance();
            ActivityUtile.addFragmentToActivity(getSupportFragmentManager(),
                    chooseAreaFragment, R.id.contentFrame);
        }

        //create presenter
        DaggerChooseAreaComponent.builder().chooseAreaModule(new ChooseAreaModule(chooseAreaFragment))
                .build().inject(this);
    }
}
