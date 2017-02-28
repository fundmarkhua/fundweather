package com.example.congbai.fundweather.model.repository;

import android.content.Context;

import com.example.congbai.fundweather.model.impl.ChooseAreaImpl;
import com.example.congbai.fundweather.util.ToastUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by fundmarkhua on 2017/2/27
 * Email:57525101@qq.com
 */
@Singleton
public class ChooseAreaRepository implements ChooseAreaImpl {
    @Inject
    Context context;
    @Inject
    ToastUtil toastUtil;

    @Inject
    ChooseAreaRepository() {
    }

    @Override
    public String getCityTest() {
        return "河南";
    }

    @Override
    public boolean saveProvince() {
        toastUtil.showToast("存进去了,其实是骗你的");
        return true;
    }
}
