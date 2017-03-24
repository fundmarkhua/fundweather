package com.example.congbai.fundweather.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;

import java.util.Locale;

/**
 * Created by fundmarkhua on 2017/3/22
 * Email:57525101@qq.com
 * 设备相关的工具类
 */

public class DeviceUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
     *
     * @param context 上下文
     * @param pxValue 尺寸像素
     * @return SP值
     */
    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px
     *
     * @param context 上下文
     * @param spValue SP值
     * @return 像素值
     */
    public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取dialog宽度
     *
     * @param activity Activity
     * @return Dialog宽度
     */
    public static int getDialogW(Activity activity) {
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        //int w = dm.widthPixels - 100;
        // int w = aty.getWindowManager().getDefaultDisplay().getWidth() - 100;
        return dm.widthPixels - 100;
    }

    /**
     * 获取屏幕宽度
     *
     * @param activity Activity
     * @return 屏幕宽度
     */
    public static int getScreenW(Context activity) {
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return 屏幕高度
     */
    public static int getScreenH(Context mContext) {
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        // int h = aty.getWindowManager().getDefaultDisplay().getHeight();
        return dm.heightPixels;
    }

    public static int getWindowTitleH(Activity mActivity) {
        Rect frame = new Rect();
        mActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    /**
     * 判断当前系统是否是中文
     *
     * @return 判断值
     */
    public static boolean isZh(Context mContext) {
        Locale locale;
        if (Build.VERSION.SDK_INT >= 24) {
            locale = mContext.getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = mContext.getResources().getConfiguration().locale;
        }
        String language = locale.getLanguage();
        return language.endsWith("zh");
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getFactory() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 手机唯一标识码IMEI
     *
     * @param context 上下文
     * @return 标识码
     */
    public static String getIMEI(Context context) {
        String imei = null;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            imei = tm.getDeviceId();       //取出IMEI
            String tel = tm.getLine1Number();     //取出MSISDN，很可能为空
            String iccid = tm.getSimSerialNumber(); //取出ICCID
            String imsi = tm.getSubscriberId();     //取出IMSI
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (imei == null || TextUtils.isEmpty(imei)) {
                imei = System.currentTimeMillis() + "";
            }
        }
        return imei;
    }

    /**
     * 获取版本名称
     *
     * @param context 上下文
     * @return 版本名称
     */
    public static String getVersionName(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        if (packageInfo != null) {
            return getPackageInfo(context).versionName;
        } else {
            return "";
        }
    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     * @return 版本号
     */
    public static int getVersionCode(Context context) {
        PackageInfo packageInfo = getPackageInfo(context);
        if (packageInfo != null) {
            return getPackageInfo(context).versionCode;
        } else {
            return 0;
        }
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressLint("NewApi")
    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (Build.VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

    public static String getDeviceInfo(Context context) {
        try {
            org.json.JSONObject json = new org.json.JSONObject();
            android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String device_id = null;
            if (checkPermission(context, Manifest.permission.READ_PHONE_STATE)) {
                device_id = tm.getDeviceId();
            }
            android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getApplicationContext().
                    getSystemService(Context.WIFI_SERVICE);
            String mac = wifi.getConnectionInfo().getMacAddress();
            json.put("mac", mac);
            if (TextUtils.isEmpty(device_id)) {
                device_id = mac;
            }
            if (TextUtils.isEmpty(device_id)) {
                device_id = android.provider.Settings.Secure.getString(context.getContentResolver(),
                        android.provider.Settings.Secure.ANDROID_ID);
            }
            json.put("device_id", device_id);
            return json.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 这个方法可以实现输入法在窗口上切换显示.
     * 如果输入法在窗口上已经显示，则隐藏;
     * 如果隐藏，则显示输入法到窗口上
     *
     * @param context 上下文
     */
    public static void toggleKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
