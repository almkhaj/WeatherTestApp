package com.akhaj.weathertestapp.adapter;

import android.util.Log;

import com.akhaj.weathertestapp.model.Weather;
import com.akhaj.weathertestapp.widget.Utils;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomJsonDeserializer implements JsonDeserializer<List<Weather>> {
    private static final String TAG_DT = "dt";
    private static final String TAG_TEMP = "temp";
    private static final String TAG_LIST = "list";
    private static final String TAG_TEMP_DAY = "day";
    private static final String TAG_TEMP_EVE = "eve";
    private static final String TAG_TEMP_NIGHT = "night";
    private static final String TAG_TEMP_MORN = "morn";
    private static final String TAG_WEATHER = "weather";
    private static final String TAG_WEATHER_ICON = "icon";
    private static final String TAG_DESCRIPTION = "description";

    @Override
    public List<Weather> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        List<Weather> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json.toString());
            JSONArray listArray = jsonObject.getJSONArray(TAG_LIST);
            for (int i = 0; i < listArray.length(); i++) {
                JSONObject listObj = (JSONObject) listArray.get(i);
                Weather item = new Weather();
                item.setDate(Long.parseLong(listObj.getString(TAG_DT)) * 1000L);
                Log.d("TAG", String.valueOf(Utils.long2Date(item.getDate())));

                JSONObject tempObj = listObj.getJSONObject(TAG_TEMP);
                item.setTemperatureDay(tempObj.getString(TAG_TEMP_DAY));
                item.setTemperatureEve(tempObj.getString(TAG_TEMP_EVE));
                item.setTemperatureNight(tempObj.getString(TAG_TEMP_NIGHT));
                item.setTemperatureMorn(tempObj.getString(TAG_TEMP_MORN));

                JSONArray weatherArray = listObj.getJSONArray(TAG_WEATHER);
                if (weatherArray.length() >= 1) {
                    JSONObject weatherObj = (JSONObject) weatherArray.get(0);
                    item.setIcon(weatherObj.getString(TAG_WEATHER_ICON));

                    item.setDescription(weatherObj.getString(TAG_DESCRIPTION));

                } else {
                    item.setIcon("");
                }
                list.add(item);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;
    }

}
