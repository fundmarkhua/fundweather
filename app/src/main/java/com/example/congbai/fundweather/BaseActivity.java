package com.example.congbai.fundweather;

import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 */
public class BaseActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    //返回键监听实现
    public interface FragmentBackListener {
        void onBackForward();
    }

    private FragmentBackListener backListener;
    private boolean isInterception = false;

    public FragmentBackListener getBackListener() {
        return backListener;
    }

    public void setBackListener(FragmentBackListener backListener) {
        this.backListener = backListener;
    }

    public boolean isInterception() {
        return isInterception;
    }

    public void setInterception(boolean isInterception) {
        this.isInterception = isInterception;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isInterception()) {
                if (backListener != null) {
                    backListener.onBackForward();
                    return false;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
