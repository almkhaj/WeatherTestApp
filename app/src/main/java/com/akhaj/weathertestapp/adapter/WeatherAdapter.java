package com.akhaj.weathertestapp.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akhaj.weathertestapp.R;
import com.akhaj.weathertestapp.model.Weather;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.akhaj.weathertestapp.widget.Utils.convertTemp;
import static com.akhaj.weathertestapp.widget.Utils.date2DayMonthString;
import static com.akhaj.weathertestapp.widget.Utils.long2Date;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private List<Weather> weatherList;
    private final Context context;

    public WeatherAdapter(Context context, List<Weather> weatherList) {
        this.context = context;
        this.weatherList = weatherList;
    }

    @Override
    public WeatherHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent,
                false);
        return new WeatherHolder(v);
    }

    @Override
    public void onBindViewHolder(WeatherHolder holder, int position) {
        Weather item = weatherList.get(position);

        holder.textDescription.setText(item.getDescription());
        holder.textDate.setText(date2DayMonthString(long2Date(item.getDate())));
        holder.textTempMorn.setText(convertTemp(item.getTemperatureMorn()));
        holder.textTempDay.setText(convertTemp(item.getTemperatureDay()));
        holder.textTempEve.setText(convertTemp(item.getTemperatureEve()));
        holder.textTempNight.setText(convertTemp(item.getTemperatureNight()));

        try {
            InputStream inputStream = context.getAssets().open(item.getIcon() + ".png");
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            holder.imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class WeatherHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textDate, textTempMorn, textTempDay, textTempEve, textTempNight, textDescription;

        WeatherHolder(View view) {
            super(view);
            this.imageView = view.findViewById(R.id.image);
            this.textDate = view.findViewById(R.id.textDate);
            this.textTempMorn = view.findViewById(R.id.textTempMorning);
            this.textTempDay = view.findViewById(R.id.textTempDay);
            this.textTempEve = view.findViewById(R.id.textTempEvening);
            this.textTempNight = view.findViewById(R.id.textTempNight);
            this.textDescription = view.findViewById(R.id.textDescription);
        }
    }
}
