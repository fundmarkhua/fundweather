package com.example.congbai.fundweather.task.fragment;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.LocationClientOption;
import com.example.congbai.fundweather.BaseActivity;
import com.example.congbai.fundweather.BaseFragment;
import com.example.congbai.fundweather.R;
import com.example.congbai.fundweather.model.network.gson.AreaData;
import com.example.congbai.fundweather.task.activity.ShowWeatherActivity;
import com.example.congbai.fundweather.task.adapater.AreaAdapter;
import com.example.congbai.fundweather.task.contract.ChooseAreaContract;
import com.example.congbai.fundweather.util.ActivityCollector;
import com.example.congbai.fundweather.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by fundmarkhua on 2017/2/24
 * Email:57525101@qq.com
 * This specifies the contract between the view and the presenter.
 */

public class ChooseAreaFragment extends BaseFragment implements ChooseAreaContract.View {
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
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //rv_area.setLayoutManager(linearLayoutManager);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        rv_area.setLayoutManager(layoutManager);
        //设置每行分割线
        //rv_area.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST));
        areaAdapter = new AreaAdapter(dataList);
        rv_area.setAdapter(areaAdapter);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String weatherString = prefs.getString("weather", null);
        Boolean isChooseArea = getActivity().getIntent().getBooleanExtra("chooseArea", false);
        if (weatherString != null && !isChooseArea) {
            Intent intent = new Intent(getActivity(), ShowWeatherActivity.class);
            startActivity(intent);
            getActivity().finish();
        }

        areaAdapter.setmOnItemClickListener(new AreaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (currentLevel == LEVEL_PROVINCE) {
                    if (position == 0) {
                        String weatherId = mAreaDataList.get(position).getWeather_id();
                        Intent intent = new Intent(getActivity(), ShowWeatherActivity.class);
                        intent.putExtra("weather_id", weatherId);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    selectedArea = mAreaDataList.get(position);
                    provinceName = selectedArea.getName();
                    mPresenter.getCityData(selectedArea.getId(), "province");
                } else if (currentLevel == LEVEL_CITY) {
                    selectedArea = mAreaDataList.get(position);
                    mPresenter.getCountyData(selectedArea.getId());
                } else if (currentLevel == LEVEL_COUNTY) {
                    //selectedArea = mAreaDataList.get(position);
                    String weatherId = mAreaDataList.get(position).getWeather_id();
                    Intent intent = new Intent(getActivity(), ShowWeatherActivity.class);
                    intent.putExtra("weather_id", weatherId);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        initPermission();
        mPresenter.getProvinceData();
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
            initPermission();
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
            super.onBackForward();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //mLocationClient.stop();
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
            actionBar.setTitle(getString(R.string.china));
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
    public void onAttach(Context context) {
        super.onAttach(context);
        ((BaseActivity) getActivity()).setInterception(true);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(getActivity(), "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            ActivityCollector.finishAll();
                            return;
                        }
                    }
                    //requestLocation();
                } else {
                    Toast.makeText(getActivity(), "发生未知错误", Toast.LENGTH_SHORT).show();
                    ActivityCollector.finishAll();
                }
                break;
            default:
        }
    }

    private void initPermission() {
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(getActivity(), permissions, 1);
        }
    }
}
