
package com.example.congbai.fundweather;

import android.app.Fragment;

import com.example.congbai.fundweather.util.ToastUtil;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 *
 */
public abstract class BaseFragment extends Fragment {
    protected Disposable disposable;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
