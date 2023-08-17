package com.example.notificationweatherapp.model;

import com.google.gson.annotations.SerializedName;

public class NewApiResponse {

    @SerializedName("location")
    private Location locationList;

    @SerializedName("current")
    private Current currentList;

    @SerializedName("forecast")
    private Forecast forecast_list;

    public NewApiResponse(Location locationList, Current currentList, Forecast forecast_list) {
        this.locationList = locationList;
        this.currentList = currentList;
        this.forecast_list = forecast_list;
    }

    public Forecast getForecast_list() {
        return forecast_list;
    }

    public void setForecast_list(Forecast forecast_list) {
        this.forecast_list = forecast_list;
    }

    public Location getLocationList() {
        return locationList;
    }

    public void setLocationList(Location locationList) {
        this.locationList = locationList;
    }

    public Current getCurrentList() {
        return currentList;
    }

    public void setCurrentList(Current currentList) {
        this.currentList = currentList;
    }

    @Override
    public String toString() {
        return "NewApiResponse{" +
                "locationList=" + locationList +
                ", currentList=" + currentList +
                ", forecast_list=" + forecast_list +
                '}';
    }
}
