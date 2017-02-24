
package com.example.congbai.fundweather;

import android.app.Fragment;

import rx.Subscription;
/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 *
 */
public abstract class BaseFragment extends Fragment {
    protected Subscription subscription;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unsubscribe();
    }

    protected void unsubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
