package com.example.congbai.fundweather.task.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.congbai.fundweather.BaseActivity;
import com.example.congbai.fundweather.BaseFragment;
import com.example.congbai.fundweather.R;
import com.example.congbai.fundweather.model.network.gson.AreaData;
import com.example.congbai.fundweather.task.activity.ChooseAreaActivity;
import com.example.congbai.fundweather.task.activity.ShowWeatherActivity;
import com.example.congbai.fundweather.task.adapater.AreaAdapter;
import com.example.congbai.fundweather.task.contract.ChooseAreaContract;
import com.example.congbai.fundweather.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 * This specifies the contract between the view and the presenter.
 */

public class ChooseAreaFragment extends BaseFragment implements ChooseAreaContract.View, BaseActivity.FragmentBackListener {
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    private ChooseAreaContract.Presenter mPresenter;
    private ProgressDialog progressDialog;
    private AreaAdapter areaAdapter;
    private List<String> dataList = new ArrayList<>();
    private List<AreaData> mAreaDataList;
    private String provinceName;
    ActionBar actionBar;
    /**
     * 选中的区域
     */
    private AreaData selectedArea;
    /**
     * 当前选中级别
     */
    private int currentLevel;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_area)
    RecyclerView rv_area;

    public static ChooseAreaFragment newInstance() {
        return new ChooseAreaFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_choose_area, container, false);
        ButterKnife.bind(this, root);
        //设置toolbar
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv_area.setLayoutManager(linearLayoutManager);
        //设置每行分割线
        rv_area.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        areaAdapter = new AreaAdapter(dataList);

        rv_area.setAdapter(areaAdapter);
        //bu_text.setVisibility(View.GONE);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String weatherString = prefs.getString("weather", null);
        if (weatherString != null) {
            Intent intent = new Intent(getActivity(), ShowWeatherActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        areaAdapter.setmOnItemClickListener(new AreaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedArea = mAreaDataList.get(position);
                    provinceName = selectedArea.getName();
                    mPresenter.getCityData(selectedArea.getId(), "province");
                } else if (currentLevel == LEVEL_CITY) {
                    selectedArea = mAreaDataList.get(position);
                    mPresenter.getCountyData(selectedArea.getId());
                } else if (currentLevel == LEVEL_COUNTY) {
                    //selectedArea = mAreaDataList.get(position);
                    String weatherId = mAreaDataList.get(position).getWeather_id();
                    try {
                        Intent intent = new Intent(getActivity(), ShowWeatherActivity.class);
                        intent.putExtra("weather_id", weatherId);
                        startActivity(intent);
                    } catch (Exception e) {
                        Log.e(TAG, "onItemClick: ", e);
                    }
                    getActivity().finish();
                }
            }
        });
        mPresenter.getProvinceData() ;
    }

    /**
     * 按下左上回退按钮或者手机返回键的时候触发
     *
     * @return 是否成功
     */
    public boolean backUp() {
        if (currentLevel == LEVEL_COUNTY) {
            mPresenter.getCityData(selectedArea.getId(), "county");
            selectedArea = null;
        } else if (currentLevel == LEVEL_CITY) {
            mPresenter.getProvinceData();
        } else if (currentLevel == LEVEL_PROVINCE) {
            return false;
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //捕捉左上角回退键
        if (item.getItemId() == android.R.id.home) {
            if (!backUp()) {
                return super.onOptionsItemSelected(item);
            }
        }
        return true;
    }

    @Override
    public void onBackForward() {
        //捕捉返回键
        if (!backUp()) {
            getActivity().finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof ChooseAreaActivity) {
            ((ChooseAreaActivity) getActivity()).setBackListener(this);
            ((ChooseAreaActivity) getActivity()).setInterception(true);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (getActivity() instanceof ChooseAreaActivity) {
            ((ChooseAreaActivity) getActivity()).setBackListener(null);
            ((ChooseAreaActivity) getActivity()).setInterception(false);
        }
    }

    @Override
    public void setPresenter(@NonNull ChooseAreaContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void toastMessage(String message) {
    }

    @Override
    public void showProvince(List<AreaData> areaDataList) {
        if (actionBar != null) {
            actionBar.setTitle("中国");
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }
        if (areaDataList.size() > 0) {
            dataList.clear();
            for (AreaData areaData : areaDataList) {
                dataList.add(areaData.getName());
            }
            areaAdapter.notifyDataSetChanged();
            rv_area.scrollToPosition(0);
            currentLevel = LEVEL_PROVINCE;
            mAreaDataList = areaDataList;
        }
    }

    @Override
    public void showCity(List<AreaData> areaDataList) {
        if (actionBar != null) {
            if (selectedArea != null) {
                actionBar.setTitle(selectedArea.getName());
            } else {
                actionBar.setTitle(provinceName);
            }
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (areaDataList.size() > 0) {
            dataList.clear();
            for (AreaData areaData : areaDataList) {
                dataList.add(areaData.getName());
            }
            areaAdapter.notifyDataSetChanged();
            rv_area.scrollToPosition(0);
            currentLevel = LEVEL_CITY;
            mAreaDataList = areaDataList;
        }
    }

    @Override
    public void showCounty(List<AreaData> areaDataList) {
        if (actionBar != null) {
            actionBar.setTitle(selectedArea.getName());
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        if (areaDataList.size() > 0) {
            dataList.clear();
            for (AreaData areaData : areaDataList) {
                dataList.add(areaData.getName());
            }
            areaAdapter.notifyDataSetChanged();
            rv_area.scrollToPosition(0);
            currentLevel = LEVEL_COUNTY;
            mAreaDataList = areaDataList;
        }
    }


    @Override
    public void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage(getString(R.string.onLoad));
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
