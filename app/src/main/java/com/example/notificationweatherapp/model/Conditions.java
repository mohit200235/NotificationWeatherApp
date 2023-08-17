package com.example.notificationweatherapp.model;

import com.google.gson.annotations.SerializedName;

public class Conditions {

    @SerializedName("text")
    private String text;
    @SerializedName("icon")
    private String icon;

    public Conditions(String text, String icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Conditions{" +
                "text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
