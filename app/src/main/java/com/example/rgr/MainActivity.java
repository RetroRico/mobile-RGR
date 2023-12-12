package com.example.rgr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    EditText editTextCity;
    Button buttonGetWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCity = findViewById(R.id.city_editText);
        buttonGetWeather = findViewById(R.id.res_button);




        buttonGetWeather.setOnClickListener(view -> {

            String city = editTextCity.getText().toString().trim();
            city = city.toLowerCase();

            if(city.isEmpty()) {
                editTextCity.setError("Введiть назву мiста");
            }else if(city.matches(".*[А-Яа-яЁё].*")){
                editTextCity.setError("Введiть назву мiста на англ. мовi");

            }else{
                //Перехід на наступний Activity якщо заповнено місто

                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                intent.putExtra("CITY_NAME", city);
                startActivity(intent);
            }
        });

        Button exit_button = findViewById(R.id.exit_button);
        exit_button.setOnClickListener(view -> finishAffinity());
    }
}
