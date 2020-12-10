package com.kapitanovslog.dailyinfoapp.service;

import com.kapitanovslog.dailyinfoapp.model.Weather;

public interface WeatherService {
    Weather getWeatherByLocation(String location);
}
