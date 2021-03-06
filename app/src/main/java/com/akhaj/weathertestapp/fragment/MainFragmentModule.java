package com.akhaj.weathertestapp.fragment;

import android.support.v4.app.Fragment;

import com.akhaj.weathertestapp.di.PerFragment;

import dagger.Binds;
import dagger.Module;

@Module(includes = BaseFragmentModule.class)
public abstract class MainFragmentModule {
    @Binds
    @PerFragment
    abstract Fragment fragment(MainFragment fragment);
}
