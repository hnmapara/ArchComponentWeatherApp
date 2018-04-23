package com.example.android.sunshine.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.sunshine.data.SunshineRepository;
import com.example.android.sunshine.data.database.ListViewWeatherEntry;
import com.example.android.sunshine.data.database.WeatherEntry;

import java.util.Date;
import java.util.List;

public class MainActivityViewModel  extends ViewModel {
    // Weather forecasts the user is looking at
    private LiveData<List<ListViewWeatherEntry>> mForecasts;

    public MainActivityViewModel(SunshineRepository repository) {
        mForecasts = repository.getCurrentWeatherForecasts();
    }

    public LiveData<List<ListViewWeatherEntry>> getForecast() {
        return mForecasts;
    }
}
