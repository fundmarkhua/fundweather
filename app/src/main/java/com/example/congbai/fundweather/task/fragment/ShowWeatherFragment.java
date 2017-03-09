package com.example.congbai.fundweather.task.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.example.congbai.fundweather.BaseActivity;
import com.example.congbai.fundweather.BaseFragment;
import com.example.congbai.fundweather.R;
import com.example.congbai.fundweather.model.network.gson.Weather;
import com.example.congbai.fundweather.task.contract.ChooseAreaContract;
import com.example.congbai.fundweather.task.contract.ShowWeatherContract;
import com.example.congbai.fundweather.util.ToastUtil;

import org.w3c.dom.Text;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Cache;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by fundmarkhua on 2017/3/7
 * Email:57525101@qq.com
 */

public class ShowWeatherFragment extends BaseFragment implements ShowWeatherContract.View, BaseActivity.FragmentBackListener {
    private ShowWeatherContract.Presenter mPresenter;

    ActionBar actionBar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
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
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        actionBar.setTitle("天气预报");
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String weatherString = prefs.getString("weather", null);
        String weatherId = getActivity().getIntent().getStringExtra("weather_id");
        if (weatherString != null) {
            mPresenter.getWeatherDataByString(weatherString);
        } else {
            weatherLayout.setVisibility(View.INVISIBLE);
            mPresenter.getWeatherDataById(weatherId);
        }
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
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
        mPresenter.loadBingPic();
    }

    @Override
    public void showExceptionMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBackgroundImage(String imgUrl) {
        Glide.with(getActivity()).load(imgUrl).into(ivPic);
    }

    @Override
    public void onBackForward() {

    }
}
