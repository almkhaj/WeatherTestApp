package com.akhaj.weathertestapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.akhaj.weathertestapp.R;
import com.akhaj.weathertestapp.fragment.MainFragment;
import com.akhaj.weathertestapp.fragment.WeatherFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainFragment.OnWeatherButtonClick {

    @Inject
    FragmentManager fragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            addFragment(R.id.container, new MainFragment());
        }
    }

    @Override
    public void onWeatherButtonClick() {
        addFragment(R.id.container, new WeatherFragment());
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = fragmentManager.findFragmentByTag("WeatherFragment");
        if (fragment == null) {
            finish();
        } else {
            fragmentManager.popBackStack();
        }
    }
}
