package com.example.congbai.fundweather.task.presenter;

import com.example.congbai.fundweather.model.repository.ChooseAreaRepository;
import com.example.congbai.fundweather.task.contract.ChooseAreaContract;
import com.example.congbai.fundweather.util.ToastUtil;

import javax.inject.Inject;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 */

public class ChooseAreaPresenter implements ChooseAreaContract.Presenter {
    private final ChooseAreaRepository mChooseAreaRepository;
    private final ChooseAreaContract.View mChooseAreaView;
    private final ToastUtil mToastUtil;

    @Inject
    ChooseAreaPresenter(ChooseAreaContract.View ChooseAreaView,ChooseAreaRepository chooseAreaRepository,ToastUtil toastUtil) {
        mChooseAreaView = ChooseAreaView;
        mChooseAreaRepository = chooseAreaRepository;
        mToastUtil = toastUtil;
    }

    @Inject
    void setupListeners() {
        mChooseAreaView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void saveProvince() {
        mChooseAreaRepository.saveProvince();
    }

    @Override
    public void getMessage() {
        String cityName = mChooseAreaRepository.getCityTest();
        mToastUtil.showToast(cityName);
    }

}
