package com.example.notificationweatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.notificationweatherapp.adapter.TodayRecyclerViewAdapter;
import com.example.notificationweatherapp.model.Conditions;
import com.example.notificationweatherapp.model.Current;
import com.example.notificationweatherapp.model.Forecast;
import com.example.notificationweatherapp.model.ForecastDay;
import com.example.notificationweatherapp.model.Hour;
import com.example.notificationweatherapp.model.Location;
import com.example.notificationweatherapp.model.NewApiResponse;
import com.example.notificationweatherapp.service.NotificationService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Retrofit;

import retrofit2.Call;
;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MyBottomSheetFragment.LocationListener,
        SwipeRefreshLayout.OnRefreshListener {
    private final static String KEY = "Your_API_KEY";
    NewApiResponse newApiResponse;
    Location location_list;
    SwipeRefreshLayout srl;
    Forecast forecast_list;
    private TextView current_condition_text, temp_c, location_country, current_date;
    private ImageView current_condition_icon, add_notification;
    private RecyclerView today_recyclerView;
    private TodayRecyclerViewAdapter todayRecyclerViewAdapter;
    String currentLocationOnScreen = "Haryana";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        current_condition_text = findViewById(R.id.condition_text);
        temp_c = findViewById(R.id.temp_c);
        current_condition_icon = findViewById(R.id.condition_image);
        location_country = findViewById(R.id.location_country);
        current_date = findViewById(R.id.date);
        add_notification = findViewById(R.id.fab1);
        FloatingActionButton fab = findViewById(R.id.fab);
        today_recyclerView = findViewById(R.id.today_recycler_view);

        srl = findViewById(R.id.swipe);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        today_recyclerView.setLayoutManager(layoutManager);

        fab.setOnClickListener(view -> showBottomSheet());

        srl.setOnRefreshListener(this);
        getAPIData(currentLocationOnScreen);
    }

    public void getAPIData(String newLocation) {
        Retrofit retrofit = RetroFitClass.getRetrofitInstance();
        ApiService apiService = retrofit.create(ApiService.class);
        currentLocationOnScreen = newLocation;

        Call<NewApiResponse> apiResponseCall = apiService.getAllResponse(KEY, newLocation, 1);

        apiResponseCall.enqueue(new Callback<NewApiResponse>() {
            @Override
            public void onResponse(Call<NewApiResponse> call, Response<NewApiResponse> response) {

                newApiResponse = response.body();
                if (newApiResponse != null) {
                    getResponse(newApiResponse);
                } else {
                    Log.d("TAGMOH", "agendaModelList.toString()");
                }
            }

            private void getResponse(NewApiResponse response) {
                location_list = response.getLocationList();
                Current current_list = response.getCurrentList();
                Conditions conditions_list = current_list.getCondition();
                String url = conditions_list.getIcon();
                String url1 = "https:" + url;
                forecast_list = response.getForecast_list();

                String dateString = location_list.getCurrent_date();
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = null;
                try {
                    date = inputDateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat outputTimeFormat = new SimpleDateFormat("HH:mm");
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("MMM dd, EEE");
                String formattedDate = outputDateFormat.format(date);

                //set all textview and imageview here
                String getText = conditions_list.getText();
                current_condition_text.setText(getText);
                temp_c.setText(current_list.getTemp_c() + " °C");

                //set image
                RequestOptions requestOptions = new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(current_condition_icon.getContext())
                        .load(url1)
                        .apply(requestOptions)
                        .into(current_condition_icon);
                current_condition_icon.setClipToOutline(true);

                String placeName = location_list.getName();
                String countryName = location_list.getCountry();
                String location = placeName + " , " + countryName;
                //name and country
                location_country.setText(location);
                current_date.setText(formattedDate);

                //set the notification click
                double temp = response.getCurrentList().getTemp_c();

                add_notification.setOnClickListener(view -> {
                    Toast.makeText(MainActivity.this, "Notification is created :)", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, NotificationService.class);
                    intent.putExtra("temp", temp);
                    intent.putExtra("location", location);
                    intent.putExtra("condition", getText);
                    startService(intent);
                });

                List<ForecastDay> forecastDayList = forecast_list.getForecastDay_list();
                if (forecastDayList != null && forecastDayList.size() > 0) {
                    ForecastDay forecastDay = forecastDayList.get(0);
                    List<Hour> hourList = forecastDay.getHour_list();

                    if (hourList != null) {
                        todayRecyclerViewAdapter = new TodayRecyclerViewAdapter(hourList);
                        today_recyclerView.setAdapter(todayRecyclerViewAdapter);
                    }
                }
                srl.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<NewApiResponse> call, Throwable t) {
                Log.d("TAG", "error : complete " + t);
                srl.setRefreshing(false);
                Toast.makeText(MainActivity.this, "there is an internal issue:", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showBottomSheet() {
        MyBottomSheetFragment bottomSheetFragment = new MyBottomSheetFragment();
        bottomSheetFragment.setLocationListener(this);
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }

    @Override
    public void onLocationReceived(String location) {
        getAPIData(location);
    }

    @Override
    public void onRefresh() {
        getAPIData(currentLocationOnScreen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        srl.setRefreshing(false);
    }
}