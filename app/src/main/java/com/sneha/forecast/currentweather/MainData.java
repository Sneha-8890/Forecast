package com.sneha.forecast.currentweather;

import com.google.gson.annotations.SerializedName;

public class MainData {

    @SerializedName("temp")
    private double temperature;
    @SerializedName("feels_like")
    private double temperature_feels;
    @SerializedName("temp_min")
    private double temperature_minimum;
    @SerializedName("temp_max")
    private double temperature_maximum;

    public double getTemperature() {
        return temperature;
    }

    public double getTemperature_feels() {
        return temperature_feels;
    }

    public double getTemperature_minimum() {
        return temperature_minimum;
    }

    public double getTemperature_maximum() {
        return temperature_maximum;
    }
}
