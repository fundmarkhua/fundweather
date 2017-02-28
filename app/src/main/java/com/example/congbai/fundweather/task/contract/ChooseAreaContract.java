package com.example.congbai.fundweather.task.contract;

import com.example.congbai.fundweather.BasePresenter;
import com.example.congbai.fundweather.BaseView;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 * This specifies the contract between the view and the presenter.
 */

public interface ChooseAreaContract {
    interface View extends BaseView<Presenter> {
        void toastMessage(String message);
    }

    interface Presenter extends BasePresenter {
        void saveProvince();
        void getMessage();
    }
}
