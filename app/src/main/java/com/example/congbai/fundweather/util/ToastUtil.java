package com.example.congbai.fundweather.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by fundmarkhua on 2017/2/28
 * Email:57525101@qq.com
 * 整个app用该类来显示toast
 */

public class ToastUtil {
    private Context mContext;

    public ToastUtil(Context context) {
        mContext = context;
    }

    public void showToast(String message) {
        Toast.makeText(mContext,message,Toast.LENGTH_SHORT).show();
    }
}
