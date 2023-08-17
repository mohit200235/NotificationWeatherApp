package com.example.notificationweatherapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Location {

    @SerializedName("name")
    private String name ;
    @SerializedName("country")
    private String country ;

    @SerializedName("localtime")
    private String current_date_and_time;

    public Location(String name, String country, String current_date) {
        this.name = name;
        this.country = country;
        this.current_date_and_time = current_date;
    }

    public String getCurrent_date() {
        return current_date_and_time;
    }

    public void setCurrent_date(String current_date) {
        this.current_date_and_time = current_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @NonNull
    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", current_date=" + current_date_and_time +
                '}';
    }
}
