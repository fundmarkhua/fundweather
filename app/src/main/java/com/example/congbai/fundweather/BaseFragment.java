
package com.example.congbai.fundweather;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.util.Log;

import io.reactivex.disposables.Disposable;


/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 */
public abstract class BaseFragment extends Fragment {
    protected Disposable disposable;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.w(TAG, "onDestroyView: " );
        unsubscribe();
    }

    protected void unsubscribe() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
