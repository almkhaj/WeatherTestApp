package com.akhaj.weathertestapp.fragment;

import com.akhaj.weathertestapp.di.PerFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(modules = WeatherFragmentModule.class)
public interface WeatherFragmentSubcomponent extends AndroidInjector<WeatherFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<WeatherFragment> {
    }
}
