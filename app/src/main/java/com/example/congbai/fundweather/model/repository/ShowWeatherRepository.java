package com.example.congbai.fundweather.model.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.congbai.fundweather.MyApplication;
import com.example.congbai.fundweather.model.impl.ShowWeatherImpl;
import com.example.congbai.fundweather.model.network.NetWork;
import com.example.congbai.fundweather.model.network.gson.AreaData;
import com.example.congbai.fundweather.model.network.gson.Weather;
import com.example.congbai.fundweather.util.RealmHelper;
import com.example.congbai.fundweather.util.ToastUtil;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 */

public class ShowWeatherRepository implements ShowWeatherImpl {
    @Inject
    Context context;
    @Inject
    ToastUtil toastUtil;
    @Inject
    RealmHelper realmHelper;
    @Inject
    NetWork netWork;
    private static final String TAG = "ShowWeatherRepository";
    private String baseUrl = MyApplication.BASE_URL;
    private String remoteKey = MyApplication.REMOTE_KEY;

    @Inject
    ShowWeatherRepository() {
    }

    @Override
    public Observable<Weather> getWeatherData(String weatherId) {
        Observable<Weather> observable = netWork.getWeatherApi(baseUrl, true).getWeatherRx(weatherId, remoteKey);
        return observable
                .filter(new Predicate<Weather>() {
                    @Override
                    public boolean test(Weather weather) throws Exception {
                        List<Weather.HeWeatherBean> list = weather.getHeWeather();
                        if (list.size() > 0) {
                            Weather.HeWeatherBean heWeatherBean = list.get(0);
                            if ("ok".equals(heWeatherBean.getStatus())) {
                                return true;
                            }
                        }
                        return false;
                    }
                })
                .doOnNext(new Consumer<Weather>() {
                    @Override
                    public void accept(Weather weather) throws Exception {
                        //获取天气信息后，把json格式的数据缓存到本地
                        Gson gson = new Gson();
                        String weatherInfo = gson.toJson(weather);
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                        editor.putString("weather", weatherInfo);
                        editor.apply();
                    }
                });
    }

    @Override
    public Observable<Weather> getWeatherDataLocal(final String weatherInfo) {

        return Observable.create(new ObservableOnSubscribe<Weather>() {
            @Override
            public void subscribe(ObservableEmitter<Weather> e) throws Exception {
                Gson gson = new Gson();
                Weather weather = gson.fromJson(weatherInfo, Weather.class);
                e.onNext(weather);
            }
        });
    }

    @Override
    public Observable<String> getBingPicUrl() {
        Observable<String> observable = netWork.geBingApi(baseUrl, true).getBingPicRx();

        return observable.doOnNext(new Consumer<String>() {
            @Override
            public void accept(String bingPic) throws Exception {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
            }
        });
    }

    @Override
    public String getBingPicLocal() {
        SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(context);
        String picUrl = pre.getString("bing_pic", null);
        return picUrl;
    }
}
