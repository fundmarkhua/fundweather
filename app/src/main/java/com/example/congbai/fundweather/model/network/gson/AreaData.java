package com.example.congbai.fundweather.model.network.gson;

/**
 * Created by fundmarkhua on 2017/3/2
 * Email:57525101@qq.com
 * gson格式适配器
 */

public class AreaData {
    private int id;
    private String name;
    private String weather_id;
    private int father_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(String weather_id) {
        this.weather_id = weather_id;
    }

    public int getFather_id() {
        return father_id;
    }

    public void setFather_id(int father_id) {
        this.father_id = father_id;
    }
}
