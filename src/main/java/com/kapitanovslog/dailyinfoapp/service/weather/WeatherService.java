package com.kapitanovslog.dailyinfoapp.service.weather;

import com.kapitanovslog.dailyinfoapp.model.weather.WeatherResponse;

public interface WeatherService {
    WeatherResponse getWeatherByLocation(String location);
}
