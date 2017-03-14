
package com.example.congbai.fundweather;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;
import com.example.congbai.fundweather.util.ActivityCollector;

import io.reactivex.disposables.Disposable;


/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 */
public abstract class BaseFragment extends Fragment implements BaseActivity.FragmentBackListener {
    protected Disposable disposable;
    protected final String TAG = this.getClass().getSimpleName();
    //返回键点击间隔时间计算
    private long exitTime = 0;

    //捕捉返回键点击动作
    @Override
    public void onBackForward() {
        //和上次点击返回键的时间间隔
        long intervalTime = System.currentTimeMillis() - exitTime;
        if (intervalTime > 2000) {
            Toast.makeText(getActivity(), getString(R.string.exit_toast), Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            ActivityCollector.finishAll();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //捕捉手机返回键的时候注册监听
        ((BaseActivity) getActivity()).setBackListener(this);
        ((BaseActivity) getActivity()).setInterception(false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //捕捉手机返回键的时候取消监听
        ((BaseActivity) getActivity()).setBackListener(null);
        ((BaseActivity) getActivity()).setInterception(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.w(TAG, "onDestroyView: ");
        unsubscribe();
    }

    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
