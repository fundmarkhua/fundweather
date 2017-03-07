package com.example.congbai.fundweather.model.repository;

import android.content.Context;
import android.util.Log;

import com.example.congbai.fundweather.MyApplication;
import com.example.congbai.fundweather.model.entity.City;
import com.example.congbai.fundweather.model.entity.County;
import com.example.congbai.fundweather.model.entity.Province;
import com.example.congbai.fundweather.model.impl.ChooseAreaImpl;
import com.example.congbai.fundweather.model.network.NetWork;
import com.example.congbai.fundweather.model.network.gson.AreaData;
import com.example.congbai.fundweather.util.RealmHelper;
import com.example.congbai.fundweather.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;

/**
 * Created by fundmarkhua on 2017/2/27
 * Email:57525101@qq.com
 * 数据处理类
 */
@Singleton
public class ChooseAreaRepository implements ChooseAreaImpl {
    @Inject
    Context context;
    @Inject
    ToastUtil toastUtil;
    @Inject
    RealmHelper realmHelper;
    @Inject
    NetWork netWork;
    private static final String TAG = "ChooseAreaRepository";
    private String baseUrl = MyApplication.BASE_URL;

    private Realm mRealm;

    @Inject
    ChooseAreaRepository() {
    }

    @Override
    public String getCityTest() {
        StringBuilder cityInfo = new StringBuilder();
        try {
            mRealm = realmHelper.getRealm();
            RealmResults<County> results = mRealm.where(County.class)
                    .findAll();
            int rSize = results.size();
            for (int i = 0; i < rSize; i++) {
                cityInfo.append(" name");
                cityInfo.append(results.get(i).getCountyName());
                cityInfo.append(" id");
                cityInfo.append(results.get(i).getCityId());
                /** mRealm.beginTransaction();
                 County county = results.get(i);
                 county.deleteFromRealm();
                 mRealm.commitTransaction();

                 Province province = results.get(i);
                 cityInfo.append("id ");
                 cityInfo.append(province.getId());
                 cityInfo.append(" name");
                 cityInfo.append(province.getProvinceName());
                 cityInfo.append(" province_code");
                 cityInfo.append(province.getProvinceCode());**/
            }
        } catch (Exception e) {
            Log.w(TAG, "getCityTest: ", e);
        }
        return cityInfo.toString();
    }

    @Override
    public List<AreaData> getAreaData(String areaType, int areaId) {
        Boolean success;
        switch (areaType) {
            case "province":
                List<AreaData> provinceList = getProvinceLocal();
                success = false;
                //数据库里面没有省份数据就从网络接口获取
                if (provinceList.size() == 0) {
                    Call<List<AreaData>> call = netWork.getAreaApi(baseUrl, false).getProvinces();
                    try {
                        provinceList = call.execute().body();
                        //存入数据库
                        success = saveProvince(provinceList);
                    } catch (Exception e) {
                        Log.e(TAG, "getAreaData province from network : ", e);
                    }
                    //从数据再次读取
                    if (success) {
                        provinceList = getProvinceLocal();
                    }
                }
                return provinceList;

            case "city":
                List<AreaData> cityList = getCityLocal(areaId);
                success = false;
                //数据库里面没有省份数据就从网络接口获取
                if (cityList.size() == 0) {
                    Call<List<AreaData>> call = netWork.getAreaApi(baseUrl, false).getCities(areaId);
                    try {
                        cityList = call.execute().body();
                        //存入数据库
                        success = saveCity(cityList, areaId);
                    } catch (Exception e) {
                        Log.e(TAG, "getAreaData city : ", e);
                    }
                    if (success) {
                        cityList = getCityLocal(areaId);
                    }
                }
                return cityList;
            case "county":
                List<AreaData> countryList = getCountyLocal(areaId);
                success = false;
                //数据库里面没有省份数据就从网络接口获取
                if (countryList.size() == 0) {
                    Call<List<AreaData>> call = netWork.getAreaApi(baseUrl, false).getCounties(areaId);
                    try {
                        countryList = call.execute().body();
                        //存入数据库
                        success = saveCounty(countryList, areaId);
                    } catch (Exception e) {
                        Log.e(TAG, "getAreaData county : ", e);
                    }
                    if (success) {
                        countryList = getCountyLocal(areaId);
                    }
                }
                return countryList;
        }
        return null;
    }

    /**
     * 获取数据库内省份数据
     *
     * @return 结果集
     */
    @Override
    public List<AreaData> getProvinceLocal() {
        ArrayList<AreaData> provinceList = new ArrayList<>();
        try {
            mRealm = realmHelper.getRealm();
            RealmResults<Province> results = mRealm.where(Province.class)
                    .findAll();
            int rSize = results.size();
            AreaData areaData;
            for (int i = 0; i < rSize; i++) {
                areaData = new AreaData();
                Province province = results.get(i);
                areaData.setId(province.getProvinceCode());
                areaData.setName(province.getProvinceName());
                provinceList.add(areaData);
            }
        } catch (Exception e) {
            Log.e(TAG, "getProvinceLocal: ", e);
        } finally {
            realmHelper.close();
        }
        return provinceList;
    }

    /**
     * 获取数据库中指定省份中的城市数据
     *
     * @param provinceId 省份编码
     * @return 结果集
     */
    @Override
    public List<AreaData> getCityLocal(int provinceId) {
        ArrayList<AreaData> cityList = new ArrayList<>();
        try {
            mRealm = realmHelper.getRealm();
            RealmResults<City> results = mRealm.where(City.class)
                    .equalTo("provinceId", provinceId)
                    .findAll();
            int rSize = results.size();
            AreaData areaData;
            for (int i = 0; i < rSize; i++) {
                areaData = new AreaData();
                City city = results.get(i);
                areaData.setId(city.getCityCode());
                areaData.setName(city.getCityName());
                cityList.add(areaData);
            }
        } catch (Exception e) {
            Log.e(TAG, "getCityLocal: ", e);
        } finally {
            realmHelper.close();
        }
        return cityList;
    }

    /**
     * 获取数据库中指定城市中区县的数据
     *
     * @param cityId 城市编码
     * @return 结果集
     */
    @Override
    public List<AreaData> getCountyLocal(int cityId) {
        ArrayList<AreaData> countyList = new ArrayList<>();
        try {
            mRealm = realmHelper.getRealm();
            RealmResults<County> results = mRealm.where(County.class)
                    .equalTo("cityId", cityId)
                    .findAll();
            int rSize = results.size();
            AreaData areaData;
            for (int i = 0; i < rSize; i++) {
                areaData = new AreaData();
                County county = results.get(i);
                areaData.setId(county.getCountyCode());
                areaData.setName(county.getCountyName());
                areaData.setWeather_id(county.getWeatherId());
                countyList.add(areaData);
            }
        } catch (Exception e) {
            Log.e(TAG, "getCountyLocal: ", e);
        } finally {
            realmHelper.close();
        }
        return countyList;
    }

    @Override
    public int getProvinceId(int cityId) {
        mRealm = realmHelper.getRealm();
        int provinceId = 1;
        try {
            City city = mRealm.where(City.class).equalTo("cityCode", cityId).findFirst();

            provinceId = city.getProvinceId();
        } catch (Exception e) {
            Log.e(TAG, "getCountyLocal: ", e);
        } finally {
            realmHelper.close();
        }
        return provinceId;
    }

    /**
     * 存储省份数据到数据库
     *
     * @param provinceList 省份数据集
     * @return 存储是否成功
     */
    @Override
    public boolean saveProvince(List<AreaData> provinceList) {
        try {
            mRealm = realmHelper.getRealm();
            for (AreaData provinceData : provinceList) {
                mRealm.beginTransaction();
                //获取主键
                int preKey = realmHelper.getPrimaryKey(Province.class, "id");
                Province province = new Province();
                province.setId(preKey);
                province.setProvinceCode(provinceData.getId());
                province.setProvinceName(provinceData.getName());
                mRealm.copyToRealm(province);
                mRealm.commitTransaction();
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "saveProvince: ", e);
        } finally {
            realmHelper.close();
        }
        return false;
    }

    @Override
    public boolean saveCity(List<AreaData> cityList, int provinceId) {
        try {
            mRealm = realmHelper.getRealm();
            for (AreaData cityData : cityList) {
                mRealm.beginTransaction();
                //获取主键
                int preKey = realmHelper.getPrimaryKey(City.class, "id");
                City city = new City();
                city.setId(preKey);
                city.setCityCode(cityData.getId());
                city.setCityName(cityData.getName());
                city.setProvinceId(provinceId);
                mRealm.copyToRealm(city);
                mRealm.commitTransaction();
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "saveProvince: ", e);
        } finally {
            realmHelper.close();
        }
        return false;
    }

    @Override
    public boolean saveCounty(List<AreaData> countyList, int cityId) {
        try {
            mRealm = realmHelper.getRealm();
            for (AreaData countyData : countyList) {
                mRealm.beginTransaction();
                //获取主键
                int preKey = realmHelper.getPrimaryKey(County.class, "id");
                County county = new County();
                county.setId(preKey);
                county.setCountyCode(countyData.getId());
                county.setCountyName(countyData.getName());
                county.setWeatherId(countyData.getWeather_id());
                county.setCityId(cityId);
                mRealm.copyToRealm(county);
                mRealm.commitTransaction();
            }
            return true;
        } catch (Exception e) {
            Log.e(TAG, "saveProvince: ", e);
        } finally {
            realmHelper.close();
        }
        return false;
    }
}
