package com.akhaj.weathertestapp.model;

import android.app.Application;
import android.content.Context;

import com.akhaj.weathertestapp.adapter.CustomJsonDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private Context context;

    public ApiModule(Application app) {
        context = app;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    @Named("Weather")
    String provideBaseUrl() {
        return BASE_URL;
    }

    @Provides
    @Singleton
    Gson provideGson() {

        Type weatherListType = new TypeToken<List<Weather>>() {}.getType();

        return new GsonBuilder()
                .registerTypeAdapter(weatherListType, new CustomJsonDeserializer())
                .create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson, @Named("Weather") String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }

    @Provides
    @Singleton
    WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

}