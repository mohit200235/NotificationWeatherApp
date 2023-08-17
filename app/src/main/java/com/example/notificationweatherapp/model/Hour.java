package com.example.notificationweatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Hour {

    @SerializedName("time")
    private String time;

@SerializedName("temp_c")
    private Double temp_at_that_time;
@SerializedName("condition")
    private Condition_weather condition_weather_image;

    public Hour(String time, Double temp_at_that_time, Condition_weather condition_weather_image) {
        this.time = time;
        this.temp_at_that_time = temp_at_that_time;
        this.condition_weather_image = condition_weather_image;

    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getTemp_at_that_time() {
        return temp_at_that_time;
    }

    public void setTemp_at_that_time(Double temp_at_that_time) {
        this.temp_at_that_time = temp_at_that_time;
    }

    public Condition_weather getCondition_weather_image() {
        return condition_weather_image;
    }

    public void setCondition_weather_image(Condition_weather condition_weather_image) {
        this.condition_weather_image = condition_weather_image;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Hour{" +
                "time=" + time +
                ", temp_at_that_time=" + temp_at_that_time +
                ", condition_weather_image=" + condition_weather_image +
                '}';
    }
}
