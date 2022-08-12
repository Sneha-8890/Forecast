package com.sneha.forecast.currentweather;

import com.google.gson.annotations.SerializedName;
import com.sneha.forecast.currentweather.MainData;

import java.util.List;

public class WeatherInfo {

    @SerializedName("main")
    private MainData mainData;

    @SerializedName("weather")
    private List<Weather> weatherList;

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public MainData getMainData() {
        return mainData;
    }
}

