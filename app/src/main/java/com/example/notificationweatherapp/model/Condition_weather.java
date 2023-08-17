package com.example.notificationweatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Condition_weather {

    @SerializedName("icon")
    private String weather_at_time_image;

    public Condition_weather(String weather_at_time_image) {
        this.weather_at_time_image = weather_at_time_image;
    }

    public String getWeather_at_time_image() {
        return weather_at_time_image;
    }

    public void setWeather_at_time_image(String weather_at_time_image) {
        this.weather_at_time_image = weather_at_time_image;
    }

    @Override
    public String toString() {
        return "Condition_weather{" +
                "weather_at_time_image='" + weather_at_time_image + '\'' +
                '}';
    }
}
