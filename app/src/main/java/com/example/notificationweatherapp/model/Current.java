package com.example.notificationweatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Current {

    @SerializedName("is_day")
    private int is_day ;

    @SerializedName("temp_c")
    private Double temp_c;

    @SerializedName("condition")
    private Conditions condition;

    public Current(int is_day, Double temp_c, Conditions condition) {
        this.is_day = is_day;
        this.temp_c = temp_c;
        this.condition = condition;
    }

    public int getIs_day() {
        return is_day;
    }

    public void setIs_day(int is_day) {
        this.is_day = is_day;
    }

    public Double getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(Double temp_c) {
        this.temp_c = temp_c;
    }

    public Conditions getCondition() {
        return condition;
    }

    public void setCondition(Conditions condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Current{" +
                "is_day=" + is_day +
                ", temp_c=" + temp_c +
                ", condition=" + condition +
                '}';
    }
}
