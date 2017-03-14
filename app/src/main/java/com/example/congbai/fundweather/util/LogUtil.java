package com.example.congbai.fundweather.util;

import android.util.Log;

/**
 * Created by fundmarkhua on 2017/3/10
 * Email:57525101@qq.com
 * 全局工具类
 */

public class LogUtil {
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    public static final int NOTHING = 6;
    private static int level = WARN;

    public static void v(String tag, String msg, Throwable tr) {
        if (level <= VERBOSE) {
            Log.v(tag, msg, tr);
        }
    }

    public static void v(String tag, String msg) {
        v(tag, msg, null);
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (level <= DEBUG) {
            Log.d(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        d(tag, msg, null);
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (level <= INFO) {
            Log.i(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        i(tag, msg, null);
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (level <= WARN) {
            Log.w(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        w(tag, msg, null);
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (level <= ERROR) {
            Log.e(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        e(tag, msg, null);
    }
}
