package com.example.congbai.fundweather.util;

import android.util.Log;

import java.util.concurrent.atomic.AtomicLong;

import io.realm.Realm;

/**
 * Created by fundmarkhua on 2017/2/27
 * Email:57525101@qq.com
 */

public class RealmHelper {
    private Realm mRealm;
    private static final String TAG = "RealmHelper";
    public RealmHelper() {
    }

    public Realm getRealm() {
        mRealm = Realm.getDefaultInstance();
        return mRealm;
    }

    /**
     * 主键自增方法
     *
     * @param tableName 数据表名称
     * @param fieldName 主键字段名
     * @return 返回计算好的主键值
     */
    public int getPrimaryKey(Class tableName, String fieldName) {
        long nextKey;
        try {
            nextKey = new AtomicLong(mRealm.where(tableName).max(fieldName).longValue()).incrementAndGet();
        } catch (Exception e) {
            nextKey = 1;
            Log.w(TAG, "getPrimaryKey: ",e );
        }
        return Long.valueOf(nextKey).intValue();
    }

    public void close() {
        if (mRealm != null) {
            mRealm.close();
        }
    }
}
