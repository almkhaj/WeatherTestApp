package com.akhaj.weathertestapp.model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("forecast/daily/")
    Call<List<Weather>> getWeather(@Query("APPID") String appId,
                                   @Query("q") String town,
                                   @Query("cnt") int cnt,
                                   @Query("lang") String lang,
                                   @Query("units") String units,
                                   @Query("mode") String mode);
    @GET("forecast/daily/")
    Observable<List<Weather>> getWeatherRx(@Query("APPID") String appId,
                                           @Query("q") String town,
                                           @Query("cnt") int cnt,
                                           @Query("lang") String lang,
                                           @Query("units") String units,
                                           @Query("mode") String mode);
}
