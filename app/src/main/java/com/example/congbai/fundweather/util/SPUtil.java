package com.example.congbai.fundweather.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by fundmarkhua on 2017/3/23
 * Email:57525101@qq.com
 * SharedPreferences 存取的工具类
 */

public class SPUtil {

    private static final String SP_NAME = "config";
    private static SharedPreferences sp;

    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getBoolean(key, defValue);
    }

    public static void saveString(Context context, String key, String value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key, String defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getString(key, defValue);
    }

    public static void saveInt(Context context, String key, int value) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        sp.edit().putInt(key, value).apply();
    }

    public static int getInt(Context context, String key, int defValue) {
        if (sp == null) {
            sp = context.getSharedPreferences(SP_NAME, 0);
        }
        return sp.getInt(key, defValue);
    }
}
