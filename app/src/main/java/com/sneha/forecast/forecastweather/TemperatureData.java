package com.sneha.forecast.forecastweather;

import com.google.gson.annotations.SerializedName;
import com.sneha.forecast.currentweather.Weather;

import java.util.List;

public class TemperatureData {

    @SerializedName("temp")
    private Temp temp;

    public Temp getTemp() {
        return temp;
    }

    @SerializedName("weather")
    private List<Weather> weatherList;

    public List<Weather> getWeatherList() {
        return weatherList;
    }
}
