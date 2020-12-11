package com.kapitanovslog.dailyinfoapp.service.weather;

import com.kapitanovslog.dailyinfoapp.model.weather.Weather;

public interface WeatherService {
    Weather getWeatherByLocation(String location);
}
