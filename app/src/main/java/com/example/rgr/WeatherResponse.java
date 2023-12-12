package com.example.rgr;

import java.util.List;

public class WeatherResponse {
    private MainInfo main;
    private List<Weather> weather;
    private Wind wind;
    private Clouds clouds;

    public MainInfo getMain() {
        return main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public static class MainInfo {
        private double temp;
        private double feels_like;
        private double humidity;

        public double getTemp() {
            return temp;
        }

        public double getFeelsLike() {
            return feels_like;
        }

        public double getHumidity() {
            return humidity;
        }
    }

    public static class Weather {
        private String main;

        public String getMain() {
            return main;
        }
    }

    public static class Wind {
        private double speed;

        public double getSpeed() {
            return speed;
        }
    }

    public static class Clouds {
        private int all;

        public int getAll() {
            return all;
        }
    }
}

