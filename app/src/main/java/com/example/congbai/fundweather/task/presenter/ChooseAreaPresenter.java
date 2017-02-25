package com.example.congbai.fundweather.task.presenter;

import com.example.congbai.fundweather.task.contract.ChooseAreaContract;

import javax.inject.Inject;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 */

public class ChooseAreaPresenter implements ChooseAreaContract.Presenter {
    private ChooseAreaContract.View mChooseAreaView;

    @Inject
    ChooseAreaPresenter(ChooseAreaContract.View ChooseAreaView) {
        mChooseAreaView = ChooseAreaView;
    }

    @Inject
    void setupListeners() {
        mChooseAreaView.setPresenter(this);
    }

    @Override
    public void getMessage() {
        mChooseAreaView.toastMessage("你好丽胖胖");
    }

    @Override
    public void start() {

    }
}
