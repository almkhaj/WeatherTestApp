package com.akhaj.weathertestapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akhaj.weathertestapp.R;
import com.akhaj.weathertestapp.adapter.WeatherAdapter;
import com.akhaj.weathertestapp.model.Weather;
import com.akhaj.weathertestapp.model.WeatherApi;
import com.akhaj.weathertestapp.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class WeatherFragment extends BaseFragment {

    private static final String APP_ID = "04fd50ee0cb69be2d3f1f74920e217d2";
    private static final String QUERY_MODE = "json";
    private static final String TEMP_UNITS = "metric";
    private static final String QUERY_LANG = "ru";

    @Inject
    Context context;

    @Inject
    WeatherApi weatherApi;

    public WeatherFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final List weatherList = new ArrayList<>();
        final RecyclerView listView = getActivity().findViewById(R.id.listView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        listView.setLayoutManager(layoutManager);
        listView.setBackgroundColor(Color.WHITE);
        listView.setItemAnimator(new DefaultItemAnimator());
        listView.addItemDecoration(new DividerItemDecoration(context, R.drawable.divider));

        WeatherAdapter adapter = new WeatherAdapter(context, weatherList);
        listView.setAdapter(adapter);

        // retrofit call
        /*
        weatherApi.getWeather(APP_ID,"Vladimir,ru",2,QUERY_LANG,TEMP_UNITS,QUERY_MODE)
                .enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                weatherList.addAll(response.body());
                listView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                Log.d("TAG", t.getMessage());
            }
        });
        */


        weatherApi.getWeatherRx(APP_ID, "Vladimir,ru", 2, QUERY_LANG, TEMP_UNITS, QUERY_MODE)
                .subscribeOn(Schedulers.newThread())
                .filter(new Predicate<List<Weather>>() {
                    @Override
                    public boolean test(List<Weather> weathers) throws Exception {
                        Log.d("THREAD", "1: " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .delay(2000L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<List<Weather>>() {
                    @Override
                    public boolean test(List<Weather> weathers) throws Exception {
                        Log.d("THREAD", "2: " + Thread.currentThread().getName());
                        return true;
                    }
                })
                .subscribe(new Observer<List<Weather>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("TAG", "RX Subcribe");
                    }

                    @Override
                    public void onNext(List<Weather> weathers) {
                        weatherList.addAll(weathers);
                        listView.getAdapter().notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "RX Error");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "RX Complete");
                    }
                });
        /*
        Executor executor = new ThreadPoolExecutor(1, 1, 10000L, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());

        final Observable<List<Weather>> observable =
                weatherApi.getWeatherRx(APP_ID, "Vladimir,ru", 2, QUERY_LANG, TEMP_UNITS, QUERY_MODE);

        observable.subscribeOn(Schedulers.from(executor));


        new Thread("API") {
            @Override
            public void run() {
                Log.d("THREAD", "1: " Thread.currentThread().getName());

                observable
                        .filter(new Predicate<List<Weather>>() {
                            @Override
                            public boolean test(List<Weather> weathers) throws Exception {
                                Log.d("THREAD", "2: " + Thread.currentThread().getName());
                                return true;
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .delaySubscription(3000L, TimeUnit.MILLISECONDS)
                        .filter(new Predicate<List<Weather>>() {
                            @Override
                            public boolean test(List<Weather> weathers) throws Exception {
                                Log.d("THREAD", "3: " + Thread.currentThread().getName());
                                return true;
                            }
                        })
                        .subscribe(new Observer<List<Weather>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.d("TAG", "RX Subcribe");
                            }

                            @Override
                            public void onNext(List<Weather> weathers) {
                                weatherList.addAll(weathers);
                                listView.getAdapter().notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("TAG", "RX Error");
                            }

                            @Override
                            public void onComplete() {
                                Log.d("TAG", "RX Complete");
                            }
                        });
            }
        }.start();
        */


    }
}