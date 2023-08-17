package com.example.notificationweatherapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ForecastDay {

    @SerializedName("hour")
    private List<Hour> hour_list;

    public ForecastDay(List<Hour> hour_list) {
        this.hour_list = hour_list;
    }

    public List<Hour> getHour_list() {
        return hour_list;
    }

    public void setHour_list(List<Hour> hour_list) {
        this.hour_list = hour_list;
    }

    @NonNull
    @Override
    public String toString() {
        return "ForecastDay{" +
                "hour_list=" + hour_list +
                '}';
    }
}
