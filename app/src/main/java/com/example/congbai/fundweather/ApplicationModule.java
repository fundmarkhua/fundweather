package com.example.congbai.fundweather;

import android.content.Context;

import com.example.congbai.fundweather.model.network.NetWork;
import com.example.congbai.fundweather.util.RealmHelper;
import com.example.congbai.fundweather.util.ToastUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 * <p>
 * This is a Dagger module. We use this to pass in the Context dependency to some Component
 */
@Module
public final class ApplicationModule {
    private final Context mContext;

    ApplicationModule(Context context) {
        mContext = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return mContext;
    }

    @Singleton
    @Provides
    ToastUtil provideToastUtil() {
        return new ToastUtil(mContext);
    }

    @Singleton
    @Provides
    RealmHelper provideRealmHelper() {
        return new RealmHelper();
    }

    @Singleton
    @Provides
    NetWork provideNetWork() {
        return new NetWork();
    }

}
