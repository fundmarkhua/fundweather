package com.example.congbai.fundweather.model.network;


import com.example.congbai.fundweather.model.network.api.AreaApi;
import com.example.congbai.fundweather.model.network.api.RemoteDataApi;
import com.example.congbai.fundweather.model.network.api.WeatherApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by fundmarkhua on 2017/2/28
 * Email:57525101@qq.com
 * 网络访问类  实现若干访问网络的方法，返回
 */

public class NetWork {
    private AreaApi provinceApi;
    private WeatherApi weatherApi;
    private RemoteDataApi remoteDataApi;
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private Converter.Factory scalarsConverterFactory = ScalarsConverterFactory.create();
    //使用第三方rxJava适配器
    private CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();

    //获取省份数据
    public AreaApi getAreaApi(String baseUrl, boolean rx) {
        if (provinceApi == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(gsonConverterFactory);
            if (rx) {
                builder.addCallAdapterFactory(rxJavaCallAdapterFactory);
            }
            provinceApi = builder.build().create(AreaApi.class);

        }
        return provinceApi;
    }

    //获取天气数据
    public WeatherApi getWeatherApi(String baseUrl, boolean rx) {
        if (weatherApi == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(gsonConverterFactory);
            if (rx) {
                builder.addCallAdapterFactory(rxJavaCallAdapterFactory);
            }
            weatherApi = builder.build().create(WeatherApi.class);
        }
        return weatherApi;
    }
      //获取远程图片
    public RemoteDataApi geBingApi(String baseUrl, boolean rx) {
        if (remoteDataApi == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(scalarsConverterFactory);
            if (rx) {
                builder.addCallAdapterFactory(rxJavaCallAdapterFactory);
            }
            remoteDataApi = builder.build().create(RemoteDataApi.class);
        }
        return remoteDataApi;
    }

    //根据城市名称获取天气代码
    public AreaApi getWeatherCodeApi(String baseUrl, boolean rx) {
        if (provinceApi == null) {
            Retrofit.Builder builder = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(baseUrl)
                    .addConverterFactory(gsonConverterFactory);
            if (rx) {
                builder.addCallAdapterFactory(rxJavaCallAdapterFactory);
            }
            provinceApi = builder.build().create(AreaApi.class);
        }
        return provinceApi;
    }

}
