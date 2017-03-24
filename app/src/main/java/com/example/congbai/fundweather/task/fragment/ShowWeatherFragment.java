package com.example.congbai.fundweather.task.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.congbai.fundweather.BaseActivity;
import com.example.congbai.fundweather.BaseFragment;
import com.example.congbai.fundweather.R;
import com.example.congbai.fundweather.model.network.gson.Weather;
import com.example.congbai.fundweather.receive.MyReceive;
import com.example.congbai.fundweather.task.activity.ChooseAreaActivity;
import com.example.congbai.fundweather.task.contract.ShowWeatherContract;
import com.example.congbai.fundweather.util.ImageLoader;

import java.security.PublicKey;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 */

public class ShowWeatherFragment extends BaseFragment implements ShowWeatherContract.View {
    private ShowWeatherContract.Presenter mPresenter;
    private IntentFilter intentFilter;
    private MyReceive myReceive;
    private String mWeatherId;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    @BindView(R.id.iv_pic)
    ImageView ivPic;
    @BindView(R.id.weather_layout)
    NestedScrollView weatherLayout;
    @BindView(R.id.tv_city_name)
    TextView tvCityName;
    @BindView(R.id.tv_update_time)
    TextView tvUpdateTime;
    @BindView(R.id.tv_degree)
    TextView tvDegree;
    @BindView(R.id.tv_weather_info)
    TextView tvWeatherInfo;

    @BindView(R.id.layout_forecast)
    LinearLayout layoutForecast;
    @BindView(R.id.layout_aqi)
    LinearLayout layoutAqi;
    @BindView(R.id.tv_aqi)
    TextView tvAqi;
    @BindView(R.id.tv_pm25)
    TextView tvPm25;
    @BindView(R.id.tv_comfort_info)
    TextView tvComfortInfo;
    @BindView(R.id.tv_car_wash)
    TextView tvCarWash;
    @BindView(R.id.tv_sport_info)
    TextView tvSportInfo;


    public static ShowWeatherFragment newInstance() {
        return new ShowWeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_show_weather, container, false);
        ButterKnife.bind(this, root);
        initViews();
        initWeather();
        ScreenRegister();
        return root;
    }

    private void ScreenRegister() {
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        myReceive = new MyReceive();
        getActivity().registerReceiver(myReceive,intentFilter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(myReceive);
    }

    @Override
    public void setPresenter(@NonNull ShowWeatherContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showWeatherData(@NonNull Weather weatherData) {
        List<Weather.HeWeatherBean> beanList = weatherData.getHeWeather();
        if (beanList.size() > 0) {
            Weather.HeWeatherBean heWeatherBean = beanList.get(0);
            mWeatherId = heWeatherBean.getBasic().getWeatherId();
            tvCityName.setText(heWeatherBean.getBasic().getCityName());
            String updateTime = heWeatherBean.getBasic().getUpdate().getUpdateTime().split(" ")[1] + "更新";
            tvUpdateTime.setText(updateTime);
            String degree = heWeatherBean.getNow().getTmp() + "℃";
            tvDegree.setText(degree);
            tvWeatherInfo.setText(heWeatherBean.getNow().getCond().getWeatherInfo());
            layoutForecast.removeAllViews();
            for (Weather.HeWeatherBean.DailyForecastBean dailyForecastBean : heWeatherBean.getDaily_forecast()) {
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.weather_forecast_item, layoutForecast, false);
                TextView tvWeatherDate = (TextView) view.findViewById(R.id.tv_weather_date);
                TextView tvForecastInfo = (TextView) view.findViewById(R.id.tv_forecast_info);
                TextView tvMaxDegree = (TextView) view.findViewById(R.id.tv_max_degree);
                TextView tvMinDegree = (TextView) view.findViewById(R.id.tv_min_degree);
                tvWeatherDate.setText(dailyForecastBean.getDate());
                tvForecastInfo.setText(dailyForecastBean.getCond().getTxt_d());
                String maxDegree = dailyForecastBean.getTmp().getMax() + "℃";
                String minDegree = dailyForecastBean.getTmp().getMin() + "℃";
                tvMaxDegree.setText(maxDegree);
                tvMinDegree.setText(minDegree);
                layoutForecast.addView(view);
            }
            if (heWeatherBean.getAqi() != null) {
                tvAqi.setText(heWeatherBean.getAqi().getCity().getAqi());
                tvPm25.setText(heWeatherBean.getAqi().getCity().getPm25());
            } else {
                layoutAqi.setVisibility(View.GONE);
            }
            String comfort = "舒适度: " + heWeatherBean.getSuggestion().getComfort().getInfo();
            String carWash = "洗车指数: " + heWeatherBean.getSuggestion().getCarWash().getInfo();
            String sport = "运动建议: " + heWeatherBean.getSuggestion().getSport().getInfo();
            tvComfortInfo.setText(comfort);
            tvCarWash.setText(carWash);
            tvSportInfo.setText(sport);
            weatherLayout.setVisibility(View.VISIBLE);
        }
        srlRefresh.setRefreshing(false);
        mPresenter.loadBingPic();
    }

    @Override
    public void showExceptionMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBackgroundImage(String imgUrl) {
        //Glide.with(getActivity()).load(imgUrl).into(ivPic);
        ImageLoader.loadUrlImage(getActivity(), imgUrl, ivPic);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //捕捉左上角回退键
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getActivity(), ChooseAreaActivity.class);
            intent.putExtra("chooseArea", true);
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((BaseActivity) getActivity()).setInterception(true);
    }

    @Override
    public void showRefreshStatus() {
        //手动显示下拉刷新状态进度球
        srlRefresh.setProgressViewOffset(false, 0, 52);
        srlRefresh.setRefreshing(true);
    }

    private void initViews() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        ActionBar actionBar;
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_home);
            actionBar.setTitle(getString(R.string.app_title));
        }
        srlRefresh.setColorSchemeResources(R.color.colorPrimary);

    }

    private void initWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String weatherString = prefs.getString("weather", null);
        String weatherId = getActivity().getIntent().getStringExtra("weather_id");
        if (weatherString != null && weatherId == null) {
            mPresenter.getWeatherDataByString(weatherString);
        } else {
            weatherLayout.setVisibility(View.INVISIBLE);
            mPresenter.getWeatherDataById(weatherId);
        }
        //下拉刷新天气信息
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getWeatherDataById(mWeatherId);
            }
        });
    }


}
