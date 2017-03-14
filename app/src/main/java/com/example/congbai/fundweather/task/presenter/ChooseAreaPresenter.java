package com.example.congbai.fundweather.task.presenter;

import android.util.Log;

import com.example.congbai.fundweather.model.network.gson.AreaData;
import com.example.congbai.fundweather.model.repository.ChooseAreaRepository;
import com.example.congbai.fundweather.task.contract.ChooseAreaContract;
import com.example.congbai.fundweather.util.LogUtil;
import com.example.congbai.fundweather.util.ToastUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 */

public class ChooseAreaPresenter implements ChooseAreaContract.Presenter {
    private final ChooseAreaRepository mChooseAreaRepository;
    private final ChooseAreaContract.View mChooseAreaView;
    private final ToastUtil mToastUtil;
    private static final String TAG = "ChooseAreaPresenter";

    @Inject
    ChooseAreaPresenter(ChooseAreaContract.View ChooseAreaView, ChooseAreaRepository chooseAreaRepository, ToastUtil toastUtil) {
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
    public void getProvinceData() {
        mChooseAreaView.showProgressDialog();
        Observable.create(new ObservableOnSubscribe<List<AreaData>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AreaData>> e) throws Exception {
                List<AreaData> areaList = mChooseAreaRepository.getAreaData("province", 0);

                e.onNext(areaList);
            }
        })
                .map(new Function<List<AreaData>, List<AreaData>>() {
                    @Override
                    public List<AreaData> apply(List<AreaData> areaDatas) throws Exception {
                        String cityName = mChooseAreaRepository.getLocationCity();
                        if (cityName != null) {
                            AreaData areaData = mChooseAreaRepository.getWeatherCode(cityName.replace("市", ""));
                            areaDatas.add(0, areaData);
                        }
                        return areaDatas;
                    }
                })
                .subscribeOn(Schedulers.io())
                //仅指定事件消费的线程为安卓主线程，其他存在于IO线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AreaData>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<AreaData> value) {
                        mChooseAreaView.showProvince(value);
                        mChooseAreaView.closeProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("presenter", "省份数据获取异常: ", e);
                    }

                    @Override
                    public void onComplete() {
                        mChooseAreaView.closeProgressDialog();
                    }
                });
    }

    @Override
    public void getCityData(final int provinceId, final String stemFrom) {
        mChooseAreaView.showProgressDialog();
        Observable.create(new ObservableOnSubscribe<List<AreaData>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AreaData>> e) throws Exception {
                List<AreaData> areaList;
                if ("county".equals(stemFrom)) {
                    int newProvinceId = mChooseAreaRepository.getProvinceId(provinceId);
                    areaList = mChooseAreaRepository.getAreaData("city", newProvinceId);
                } else {
                    areaList = mChooseAreaRepository.getAreaData("city", provinceId);
                }
                e.onNext(areaList);
            }
        })
                .subscribeOn(Schedulers.io())
                //仅指定事件消费的线程为安卓主线程，其他存在于IO线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AreaData>>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<AreaData> value) {
                        mChooseAreaView.showCity(value);
                        mChooseAreaView.closeProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("presenter", "获取城市数据异常: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCountyData(final int cityId) {
        mChooseAreaView.showProgressDialog();
        Observable.create(new ObservableOnSubscribe<List<AreaData>>() {
            @Override
            public void subscribe(ObservableEmitter<List<AreaData>> e) throws Exception {
                List<AreaData> areaList = mChooseAreaRepository.getAreaData("county", cityId);
                e.onNext(areaList);
            }
        })
                .subscribeOn(Schedulers.io())
                //仅指定事件消费的线程为安卓主线程，其他存在于IO线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<AreaData>>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<AreaData> value) {
                        mChooseAreaView.showCounty(value);
                        mChooseAreaView.closeProgressDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.w("presenter", "郊县数据: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getWeatherCode(String cityName) {

    }
}
