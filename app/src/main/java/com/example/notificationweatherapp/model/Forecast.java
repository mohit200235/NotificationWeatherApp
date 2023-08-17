package com.example.notificationweatherapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Forecast {

    @SerializedName("forecastday")
    private List<ForecastDay> forecastDay_list;

    public Forecast(List<ForecastDay> forecastDay_list) {
        this.forecastDay_list = forecastDay_list;
    }

    public List<ForecastDay> getForecastDay_list() {
        return forecastDay_list;
    }

    public void setForecastDay_list(List<ForecastDay> forecastDay_list) {
        this.forecastDay_list = forecastDay_list;
    }

    @NonNull
    @Override
    public String toString() {
        return "Forecast{" +
                "forecastDay_list=" + forecastDay_list +
                '}';
    }
}
