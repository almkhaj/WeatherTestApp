package com.akhaj.weathertestapp;

import android.app.Activity;

import com.akhaj.weathertestapp.activity.MainActivity;
import com.akhaj.weathertestapp.activity.MainActivitySubcomponent;
import com.akhaj.weathertestapp.model.ApiModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.multibindings.IntoMap;

@Module(includes = {AndroidSupportInjectionModule.class,
        ApiModule.class},
        subcomponents = MainActivitySubcomponent.class)
abstract class AppModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity>
    mainActivityInjectorFactory(MainActivitySubcomponent.Builder builder);
}
