package com.sneha.forecast.forecastweather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastInfo {

    @SerializedName("list")
    private List<TemperatureData> temperatureData;

    public List<TemperatureData> getTemperatureData() {
        return temperatureData;
    }

}


