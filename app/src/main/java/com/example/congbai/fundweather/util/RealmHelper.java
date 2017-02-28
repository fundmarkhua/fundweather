package com.example.congbai.fundweather.util;

import android.content.Context;

import io.realm.Realm;

/**
 * Created by fundmarkhua on 2017/2/27
 * Email:57525101@qq.com
 */

public class RealmHelper {
    private Realm mRealm;

    public RealmHelper() {

    }

    public Realm getRealm() {
        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    public void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }
}
