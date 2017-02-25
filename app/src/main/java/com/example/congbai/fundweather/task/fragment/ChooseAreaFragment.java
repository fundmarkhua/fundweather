package com.example.congbai.fundweather.task.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.congbai.fundweather.R;
import com.example.congbai.fundweather.task.contract.ChooseAreaContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 * This specifies the contract between the view and the presenter.
 */

public class ChooseAreaFragment extends Fragment implements ChooseAreaContract.View {

    private ChooseAreaContract.Presenter mPresenter;
    @BindView(R.id.button_test)
    Button buttonTest;

    public static ChooseAreaFragment newInstance() {
        return new ChooseAreaFragment();
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_choose_area, container, false);
        ButterKnife.bind(this,root);
        return root;
    }

    @OnClick(R.id.button_test)
    public void testButton(){
        mPresenter.getMessage();
    }

    @Override
    public void setPresenter(@NonNull ChooseAreaContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void toastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
