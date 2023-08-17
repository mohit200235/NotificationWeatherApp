package com.example.notificationweatherapp;

import com.example.notificationweatherapp.model.NewApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v1/forecast.json")
    Call<NewApiResponse> getAllResponse(
            @Query("key") String key,
            @Query("q") String location,
            @Query("days") int days
    );
}
