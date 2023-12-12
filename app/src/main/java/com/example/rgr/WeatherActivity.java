package com.example.rgr;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherActivity extends AppCompatActivity {
    TextView textViewWeatherInfo;
    WeatherService weatherService;
    Button ok_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        textViewWeatherInfo = findViewById(R.id.weather_textView);
        ok_button = findViewById(R.id.ok_button);

        String cityName = getIntent().getStringExtra("CITY_NAME");

        // Створення екземпляру Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Створення екземпляру WeatherService
        weatherService = retrofit.create(WeatherService.class);

        // Виконання запросу
        getWeather(cityName);

        ok_button.setOnClickListener(view -> {
            //Перехід до початку
            Intent intent1 = new Intent(WeatherActivity.this, MainActivity.class);
            startActivity(intent1);
        });
    }

    private void getWeather(String city) {
        //API немає для уникнення кражі інформації
        String apiKey = "MY_API";
        String units = "metric";

        // Виконання виведення інформації з міста
        Call<WeatherResponse> call = weatherService.getWeather(city, apiKey, units);
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        double temperature = weatherResponse.getMain().getTemp();
                        double feelsLike = weatherResponse.getMain().getFeelsLike();
                        double humidity = weatherResponse.getMain().getHumidity();
                        double windSpeed = weatherResponse.getWind().getSpeed();
                        int clouds = weatherResponse.getClouds().getAll();

                        String weatherInfo = "Weather in " + city + ": " +
                                "\nТемпература: " + temperature + "°C" +
                                "\nЗа відчуттям: " + feelsLike + "°C" +
                                "\nВологість: " + humidity + "%" +
                                "\nШвидкість вітру: " + windSpeed + "м/с" +
                                "\nХмарність: " + clouds + "%";
                        textViewWeatherInfo.setText(weatherInfo);
                    }
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                textViewWeatherInfo.setText("Помилка отримання iнформацiї");
            }
        });
    }
}
