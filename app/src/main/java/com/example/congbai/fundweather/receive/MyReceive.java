package com.example.congbai.fundweather.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.congbai.fundweather.util.LogUtil;
import com.example.congbai.fundweather.util.ToastUtil;

public class MyReceive extends BroadcastReceiver {
    private static final String TAG = "MyReceive";
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case Intent.ACTION_SCREEN_ON:
                LogUtil.w(TAG,"屏幕点亮");
                break;
            case Intent.ACTION_SCREEN_OFF:
                LogUtil.w(TAG,"屏幕熄灭");
                break;
            case "android.intent.action.BOOT_COMPLETED":
                System.out.println("");
                break;
            case "android.intent.action.BATTERY_CHANGED":
                Toast.makeText(context, "电量变化", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
