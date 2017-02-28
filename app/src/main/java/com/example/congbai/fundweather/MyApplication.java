package com.example.congbai.fundweather;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Administrator on 2017/2/23.
 * base application
 */

public class MyApplication extends Application {
    //基础数据配置
    public static final String DB_NAME = "myRealm.fundWeather";

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Real配置
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(DB_NAME)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        mApplicationComponent =  DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext())).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
