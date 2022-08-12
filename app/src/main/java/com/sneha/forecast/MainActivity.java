package com.sneha.forecast;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.sneha.forecast.currentweather.ApiCall;
import com.sneha.forecast.currentweather.WeatherInfo;
import com.sneha.forecast.forecastweather.APiCallFore;
import com.sneha.forecast.forecastweather.ForecastInfo;
import com.sneha.forecast.forecastweather.RecyclerAdapter;
import com.sneha.forecast.forecastweather.RecyclerData;
import com.sneha.forecast.forecastweather.TemperatureData;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText cityName;
    TextView result, displayCity, displayDate;
    ImageView getData, img;
    RecyclerView recyclerView;
    List<RecyclerData> list = new ArrayList<>();
    APiCallFore aPiCallFore;
    ApiCall apiCall;

    Date day = new Date();
    public static final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Forecast);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.cityName);
        result = findViewById(R.id.result);
        getData = findViewById(R.id.getData);
        img = findViewById(R.id.image);
        displayCity = findViewById(R.id.displaycityname);
        displayDate = findViewById(R.id.displaydate);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerInfo.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCall = retrofit.create(ApiCall.class);

        if(getIntent()!=null){
            cityName.setText(getIntent().getStringExtra("citynametosearch"));
        }

        try {



            cityName.setOnClickListener(v -> {
                if (cityName.getText().toString().equals("")) {
                    img.setImageResource(R.drawable.weather);
                    result.setVisibility(View.GONE);
                    list.clear();
                    displayDate.setVisibility(View.GONE);
                    displayCity.setVisibility(View.GONE);
                    result.setText("");
                    displayCity.setText("");

                }
            });



            getData.setOnClickListener(v -> {
                String city = cityName.getText().toString();

                if(getIntent()!=null && getIntent().getStringExtra("citynametosearch")!=null){
                    city = getIntent().getStringExtra("citynametosearch");
                }

                if (city.equals("")) {
                } else
                    city = String.valueOf(city.charAt(0)).toUpperCase() + city.substring(1).toLowerCase();
                String finalCity = city;

                Call<WeatherInfo> call = apiCall.getWeatherData(finalCity, ServerInfo.API_KEY, "metric");
                call.enqueue(new Callback<WeatherInfo>() {
                    @Override
                    public void onResponse(Call<WeatherInfo> call, Response<WeatherInfo> response) {
                        try {
                            WeatherInfo weatherInfo = response.body();
                            result.setVisibility(View.VISIBLE);
                            result.setText(String.format("Temperature: %s℃\nFeels Like: %s℃\nMinimum Temperature: %s℃\nMaximum Temperature: %s℃",
                                    weatherInfo.getMainData().getTemperature(), weatherInfo.getMainData().getTemperature_feels(),
                                    weatherInfo.getMainData().getTemperature_minimum(), weatherInfo.getMainData().getTemperature_maximum()));
                            Log.v("icon", weatherInfo.getWeatherList().get(0).getIcon());
                            Picasso.get()
                                    .load("https://openweathermap.org/img/wn/" + weatherInfo.getWeatherList().get(0).getIcon() + "@2x.png")
                                    .into(img);
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Invalid Input or Server Error", Toast.LENGTH_SHORT).show();
                        }
                        displayDate.setVisibility(View.VISIBLE);
                        displayCity.setVisibility(View.VISIBLE);
                        displayCity.setText(cityName.getText().toString().toUpperCase());
                        displayDate.setText(String.format("%s %s", day.getDate(), months[day.getMonth()]));
                        cityName.setText("");
                    }

                    @Override
                    public void onFailure(Call<WeatherInfo> call, Throwable t) {
                        Log.e("error", t.getLocalizedMessage());
                    }
                });

                aPiCallFore = retrofit.create(APiCallFore.class);

                Call<ForecastInfo> callFore = aPiCallFore.getForeData(finalCity, ServerInfo.API_KEY, "metric");

                callFore.enqueue(new Callback<ForecastInfo>() {
                    @Override
                    public void onResponse(Call<ForecastInfo> call, Response<ForecastInfo> response) {
                        try {
                            list.clear();
                            ForecastInfo forecastInfo = response.body();
                            for (TemperatureData temp : forecastInfo.getTemperatureData()) {
                                double tempAvg = (temp.getTemp().getDay() + temp.getTemp().getEve() + temp.getTemp().getMorning() + temp.getTemp().getNight()) / 4;
                                double tempMax = temp.getTemp().getMax();
                                double tempMin = temp.getTemp().getMin();

                                String icon = temp.getWeatherList().get(0).getIcon();

                                list.add(new RecyclerData(tempAvg, tempMax, tempMin, icon));
                                Log.v("temp", tempAvg + " " + tempMax + " " + tempMin);
                            }

                            recyclerView = findViewById(R.id.forecast_result);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false));
                            RecyclerAdapter recyclerAdapter = new RecyclerAdapter((ArrayList<RecyclerData>) list, MainActivity.this);
                            recyclerView.setAdapter(recyclerAdapter);

                            recyclerView.setVisibility(View.VISIBLE);

                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, "Invalid Input or Server Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastInfo> call, Throwable t) {

                    }
                });

            });
        } catch (Exception e) {
            Toast.makeText(this, "Invalid City name", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.location){

        }
        if(item.getItemId()==R.id.search){
            startActivity(new Intent(MainActivity.this, CitySelection.class));

        }

        return true;
    }
}