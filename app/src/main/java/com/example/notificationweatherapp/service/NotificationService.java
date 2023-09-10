package com.example.notificationweatherapp.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.example.notificationweatherapp.R;

import java.security.Provider;

public class NotificationService extends Service {

    public static final String CHANNEL_ID = "channel id";
    public static final int NOTIFICATION_ID = 100;



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Drawable drawable = ResourcesCompat.getDrawable(getResources() ,R.drawable.weather,null);

        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;

        Bitmap bitmap = bitmapDrawable.getBitmap();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;

        double temperature = intent.getDoubleExtra("temp", 0.0);
        String location = intent.getStringExtra("location");
        String condition = intent.getStringExtra("condition");

        notification = new Notification.Builder(this)
                .setLargeIcon(bitmap)
                .setSmallIcon(R.drawable.weather)
                .setContentText(location +"  ->  " + temperature + " °C" +  " ( " + condition + " )")
                .setSubText("Weather Update!")
                .setChannelId(CHANNEL_ID)
                .build();
        notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID,"New Channel"
                ,NotificationManager.IMPORTANCE_HIGH));

        notificationManager.notify(NOTIFICATION_ID,notification);
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
