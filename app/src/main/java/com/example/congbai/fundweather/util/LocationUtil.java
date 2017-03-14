package com.example.congbai.fundweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.lang.ref.WeakReference;

/**
 * Created by fundmarkhua on 2017/3/12
 * Email:57525101@qq.com
 * 百度地图api封装进行地图定位
 */

public class LocationUtil {
    private static final String TAG = "LocationUtil";
    private static LocationUtil instance;
    private LocationClient mLocationClient;
    private LocationClientOption option;
    private MyLocationListener myLocationListener;
    private BDLocation currentLocation;
    private Handler handMessage;
    private SharedPreferences sharedPreferences;
    private int locationStatus = 0;//定位状态:0 未定位  1 定位成功  2 定位失败
    private boolean isCanceled;

    private LocationUtil() {
    }

    public static LocationUtil getInstance() {
        if (null == instance) {
            instance = new LocationUtil();
        }
        return instance;
    }

    private void load(Context context) {
        mLocationClient = new LocationClient(context);
        option = new LocationClientOption();
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        myLocationListener = new MyLocationListener();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        //创建消息
        handMessage = new HandlerMessage(instance);
        isCanceled = true;
    }

    public void create(Context context) {
        if (context == null) {
            return;
        }
        load(context);
    }

    /**
     * 开始定位
     */
    public void start() {
        handMessage.sendEmptyMessage(0);
    }

    /**
     * 停止定位
     */
    public void stop() {
        handMessage.sendEmptyMessage(1);
    }

    /**
     * 重启定位
     */
    public void restart() {
        handMessage.sendEmptyMessage(2);
    }

    /**
     * 定位状态
     */
    public int getReceiveStatus() {
        return locationStatus;
    }

    /**
     * 获取定位数据
     */
    public BDLocation getLocation() {
        return currentLocation;
    }

    private static class HandlerMessage extends Handler {
        WeakReference<LocationUtil> mLocationUtil;

        HandlerMessage(LocationUtil locationUtil) {
            mLocationUtil = new WeakReference<>(locationUtil);
        }

        public void handleMessage(Message msg) {
            LocationUtil myLocationUtil = mLocationUtil.get();
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    //开始
                    myLocationUtil.mLocationClient.registerLocationListener(myLocationUtil.myLocationListener);
                    myLocationUtil.mLocationClient.start();
                    myLocationUtil.isCanceled = false;
                    break;
                case 1:
                    //停止
                    myLocationUtil.mLocationClient.unRegisterLocationListener(myLocationUtil.myLocationListener);
                    myLocationUtil.mLocationClient.stop();
                    myLocationUtil.isCanceled = true;
                    myLocationUtil.locationStatus = 0;
                    break;
                case 2:
                    //重启
                    if (!myLocationUtil.isCanceled) {
                        myLocationUtil.mLocationClient.unRegisterLocationListener(myLocationUtil.myLocationListener);
                        myLocationUtil.mLocationClient.stop();
                    }
                    myLocationUtil.mLocationClient.registerLocationListener(myLocationUtil.myLocationListener);
                    myLocationUtil.mLocationClient.start();
                    myLocationUtil.isCanceled = false;
                    myLocationUtil.locationStatus = 0;
                    break;
            }
        }
    }

    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation != null) {
                if (bdLocation.getCity() != null){
                    LogUtil.w(TAG, "地址获取成功" + bdLocation.getCity());
                    String addStr = bdLocation.getAddrStr();
                    currentLocation = bdLocation;
                    locationStatus = 1;  //定位成功
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("cityName", bdLocation.getCity());
                    editor.putString("addStr", addStr);
                    editor.putLong("locationTime", System.currentTimeMillis());
                    editor.apply();
                    instance.stop();
                }
                else
                {
                    locationStatus = 2;  //定位失败
                    instance.stop();
                }
            } else {
                locationStatus = 2;  //定位失败
                instance.stop();
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {
        }
    }
}
