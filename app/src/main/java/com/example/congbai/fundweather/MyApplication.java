package com.example.congbai.fundweather;

import android.app.Application;

import com.example.congbai.fundweather.util.RealmDatabaseMigration;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;


/**
 * Created by Administrator on 2017/2/23.
 * base application
 */

public class MyApplication extends Application {
    //基础数据配置
    public static final String DB_NAME = "myRealm.fundWeather";
    public static final String BASE_URL = "http://guolin.tech/api/";
    public static final String REMOTE_KEY = "a5f9cc8e49874ab99e9fb5b792e1d467";
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Real配置
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name(DB_NAME)
                //数据库迁移写法
                //.schemaVersion(1)//新数据库版本
                //.migration(new RealmDatabaseMigration())//检测到新版本，开始迁移数据库
                //.schemaVersion(2)
                //.deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext())).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
