package com.sneha.forecast.forecastweather;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APiCallFore {
    @GET("forecast/daily")
    Call<ForecastInfo> getForeData(@Query("q") String city,
                                              @Query("appid") String apiKey,
                                   @Query("units") String unit);
}
