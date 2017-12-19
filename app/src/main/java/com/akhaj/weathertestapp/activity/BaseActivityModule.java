package com.akhaj.weathertestapp.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.akhaj.weathertestapp.di.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class BaseActivityModule {
    @Provides
    @PerActivity
    static FragmentManager activityFragmentManager(AppCompatActivity activity) {
        return activity.getSupportFragmentManager();
    }
}