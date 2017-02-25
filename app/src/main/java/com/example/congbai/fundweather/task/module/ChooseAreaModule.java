package com.example.congbai.fundweather.task.module;

import com.example.congbai.fundweather.task.contract.ChooseAreaContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fundmarkhua on 2017/2/25
 * Email:57525101@qq.com
 */
@Module
public class ChooseAreaModule {
    private final ChooseAreaContract.View mView;

    public ChooseAreaModule(ChooseAreaContract.View View) {
        mView = View;
    }

    @Provides
    ChooseAreaContract.View provideChooseContractView() {
        return mView;
    }

}
