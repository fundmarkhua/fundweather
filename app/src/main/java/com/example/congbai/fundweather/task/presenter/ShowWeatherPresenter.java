package com.example.congbai.fundweather.task.presenter;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.congbai.fundweather.MyApplication;
import com.example.congbai.fundweather.R;
import com.example.congbai.fundweather.model.network.gson.AreaData;
import com.example.congbai.fundweather.model.network.gson.Weather;
import com.example.congbai.fundweather.model.repository.ChooseAreaRepository;
import com.example.congbai.fundweather.model.repository.ShowWeatherRepository;
import com.example.congbai.fundweather.task.contract.ChooseAreaContract;
import com.example.congbai.fundweather.task.contract.ShowWeatherContract;
import com.example.congbai.fundweather.util.ToastUtil;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 */

public class ShowWeatherPresenter implements ShowWeatherContract.Presenter {
    private final ShowWeatherRepository mShowWeatherRepository;
    private final ShowWeatherContract.View mShowWeatherView;
    private final ToastUtil mToastUtil;
    private static final String TAG = "ShowWeatherPresenter";

    @Inject
    ShowWeatherPresenter(ShowWeatherContract.View ShowWeatherView, ShowWeatherRepository showWeatherRepository, ToastUtil toastUtil) {
        mShowWeatherView = ShowWeatherView;
        mShowWeatherRepository = showWeatherRepository;
        mToastUtil = toastUtil;
    }

    @Inject
    void setupListeners() {
        mShowWeatherView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getWeatherDataById(String WeatherId) {
        mShowWeatherView.showRefreshStatus();
        mShowWeatherRepository.getWeatherData(WeatherId)
                .subscribeOn(Schedulers.io())
                //仅指定事件消费的线程为安卓主线程，其他存在于IO线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Weather value) {
                        mShowWeatherView.showWeatherData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mShowWeatherView.showExceptionMessage("天气数据获取异常");
                        Log.e("presenter", "天气数据异常: ", e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });

    }

    @Override
    public void getWeatherDataByString(String weatherInfo) {
        mShowWeatherRepository.getWeatherDataLocal(weatherInfo)
                .subscribeOn(Schedulers.io())
                //仅指定事件消费的线程为安卓主线程，其他存在于IO线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Weather value) {
                        mShowWeatherView.showWeatherData(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mShowWeatherView.showExceptionMessage("天气数据获取异常");
                        Log.e("presenter", "天气数据异常: ", e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void loadBingPic() {
        String bingPicUrl = mShowWeatherRepository.getBingPicLocal();
        if (bingPicUrl != null) {
            mShowWeatherView.showBackgroundImage(bingPicUrl);
        } else {
            mShowWeatherRepository.getBingPicUrl()
                    .subscribeOn(Schedulers.io())
                    //仅指定事件消费的线程为安卓主线程，其他存在于IO线程
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(String value) {
                            mShowWeatherView.showBackgroundImage(value);
                        }

                        @Override
                        public void onError(Throwable e) {
                            mShowWeatherView.showExceptionMessage("Bing图片读取异常");
                            Log.e("presenter", "Bing图片读取异常: ", e);
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }


    }
}
