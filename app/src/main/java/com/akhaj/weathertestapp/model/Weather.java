package com.akhaj.weathertestapp.model;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("dt")
    private long mDate;
    @SerializedName("day")
    private String mTemperatureDay;
    @SerializedName("eve")
    private String mTemperatureEve;
    @SerializedName("morn")
    private String mTemperatureMorn;
    @SerializedName("night")
    private String mTemperatureNight;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("icon")
    private String mIcon;

    public Weather() {
    }

    public void setDate(long date) {
        this.mDate = date;
    }

    public void setTemperatureDay(String temperatureDay) {
        this.mTemperatureDay = temperatureDay;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public void setIcon(String icon) {
        this.mIcon = icon;
    }

    public long getDate() {
        return mDate;
    }

    public String getTemperatureDay() {
        return mTemperatureDay;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getIcon() {
        return mIcon;
    }

    public String getTemperatureEve() {
        return mTemperatureEve;
    }

    public void setTemperatureEve(String temperatureEve) {
        this.mTemperatureEve = temperatureEve;
    }

    public String getTemperatureMorn() {
        return mTemperatureMorn;
    }

    public void setTemperatureMorn(String temperatureMorn) {
        this.mTemperatureMorn = temperatureMorn;
    }

    public String getTemperatureNight() {
        return mTemperatureNight;
    }

    public void setTemperatureNight(String temperatureNight) {
        this.mTemperatureNight = temperatureNight;
    }

    public Weather(long date, String temperatureMorn, String temperatureDay,
                   String temperatureEve, String temperatureNight, String description,
                   String icon) {
        this.mDate = date;
        this.mTemperatureMorn = temperatureMorn;
        this.mTemperatureDay = temperatureDay;
        this.mTemperatureEve = temperatureEve;
        this.mTemperatureNight = temperatureNight;
        this.mDescription = description;
        this.mIcon = icon;
    }
}
