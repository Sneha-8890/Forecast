package com.sneha.forecast.forecastweather;

public class RecyclerData {

    private double temperature;
    private double temperature_max;
    private double temperature_min;
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public RecyclerData(double temperature, double temperature_max, double temperature_min, String icon) {
        this.temperature = temperature;
        this.temperature_max = temperature_max;
        this.temperature_min = temperature_min;
        this.icon = icon;
    }

    public double getTemperature() {
        return Math.round(temperature*100)/100;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature_max() {
        return Math.round(temperature_max*100)/100.0;
    }

    public void setTemperature_max(double temperature_max) {
        this.temperature_max = temperature_max;
    }

    public double getTemperature_min() {
        return Math.round(temperature_min*100)/100.0;
    }

    public void setTemperature_min(double temperature_min) {
        this.temperature_min = temperature_min;
    }
}
