package com.example.congbai.fundweather.task.activity;

import android.os.Bundle;
import com.example.congbai.fundweather.BaseActivity;
import com.example.congbai.fundweather.MyApplication;
import com.example.congbai.fundweather.R;
import com.example.congbai.fundweather.task.component.DaggerChooseAreaComponent;
import com.example.congbai.fundweather.task.fragment.ChooseAreaFragment;
import com.example.congbai.fundweather.task.module.ChooseAreaModule;
import com.example.congbai.fundweather.task.presenter.ChooseAreaPresenter;
import com.example.congbai.fundweather.util.ActivityUtile;
import com.example.congbai.fundweather.util.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 * 用于控制fragment
 */
public class ChooseAreaActivity extends BaseActivity {

    @Inject
    ChooseAreaPresenter mChooseAreaPresenter;
    @Inject
    ToastUtil toastUtil;


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
