package com.example.notificationweatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.notificationweatherapp.R;
import com.example.notificationweatherapp.model.Condition_weather;
import com.example.notificationweatherapp.model.Hour;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TodayRecyclerViewAdapter extends RecyclerView.Adapter<TodayRecyclerViewAdapter.ViewHolder> {

    private final List<Hour> hourList;

    public TodayRecyclerViewAdapter(List<Hour> hourList) {
        this.hourList = hourList;
    }

    @NonNull
    @Override
    public TodayRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_weather_recycler,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodayRecyclerViewAdapter.ViewHolder holder, int position) {

        Hour hourList1 = hourList.get(position);
        Double hour_temp = hourList1.getTemp_at_that_time();
        String hour_time = hourList1.getTime();

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;

        try {
            date = inputDateFormat.parse(hour_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputTimeFormat = new SimpleDateFormat("HH");
        Integer formattedTime = Integer.valueOf(outputTimeFormat.format(date));

        String amPm;
        if (formattedTime > 12) {
            amPm = "PM";
            formattedTime=formattedTime-12;
            holder.hour_Condition_time.setText(formattedTime+" "+amPm);
        }else if (formattedTime == 12){
            amPm = "PM";
            holder.hour_Condition_time.setText(formattedTime+" "+amPm);
        } else if (formattedTime == 0) {
            amPm ="AM";
            formattedTime =12;
            holder.hour_Condition_time.setText(formattedTime+" "+amPm);

        } else {
            amPm = "AM";
            holder.hour_Condition_time.setText(formattedTime + " "+amPm);
        }

        Condition_weather condition_weather = hourList1.getCondition_weather_image();
        String url = condition_weather.getWeather_at_time_image();
        String url1 = "https:" + url;

        String temperatureText = hour_temp + " \u00B0C";

        holder.hour_temp_C.setText(temperatureText);

        Glide.with(holder.itemView.getContext())
                .load(url1)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.hour_condition_icon);
        holder.hour_condition_icon.setClipToOutline(true);


    }

    @Override
    public int getItemCount() {
        return hourList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView hour_Condition_time,hour_temp_C;
        ImageView hour_condition_icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hour_Condition_time=itemView.findViewById(R.id.hour_condition_time);
            hour_condition_icon =itemView.findViewById(R.id.hour_condition_icon);
            hour_temp_C =itemView.findViewById(R.id.hour_temp_c);
        }
    }
}
