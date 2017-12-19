package com.akhaj.weathertestapp.fragment;

import com.akhaj.weathertestapp.di.PerFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@PerFragment
@Subcomponent(modules = MainFragmentModule.class)
public interface MainFragmentSubcomponent extends AndroidInjector<MainFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainFragment> {
    }
}
