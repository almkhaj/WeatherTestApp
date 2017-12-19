package com.akhaj.weathertestapp.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.akhaj.weathertestapp.di.PerActivity;
import com.akhaj.weathertestapp.fragment.MainFragment;
import com.akhaj.weathertestapp.fragment.MainFragmentSubcomponent;
import com.akhaj.weathertestapp.fragment.WeatherFragment;
import com.akhaj.weathertestapp.fragment.WeatherFragmentSubcomponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(includes = BaseActivityModule.class,
        subcomponents = {MainFragmentSubcomponent.class,
                WeatherFragmentSubcomponent.class})
public abstract class MainActivityModule {

    @Binds
    @IntoMap
    @FragmentKey(MainFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    mainFragmentInjectorFactory(MainFragmentSubcomponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(WeatherFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    weatherFragmentInjectorFactory(WeatherFragmentSubcomponent.Builder builder);

    @Binds
    @PerActivity
    abstract AppCompatActivity activity(MainActivity mainActivity);
}