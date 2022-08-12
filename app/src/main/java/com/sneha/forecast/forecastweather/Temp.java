package com.sneha.forecast.forecastweather;

import com.google.gson.annotations.SerializedName;

public class Temp {
    @SerializedName("morn")
    private double morning;

    @SerializedName("day")
    private double day;

    @SerializedName("eve")
    private double eve;

    @SerializedName("night")
    private double night;

    @SerializedName("max")
    private double max;

    @SerializedName("min")
    private double min;

    public double getMorning() {
        return morning;
    }

    public double getDay() {
        return day;
    }

    public double getEve() {
        return eve;
    }

    public double getNight() {
        return night;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
