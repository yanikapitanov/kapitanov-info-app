package com.kapitanovslog.dailyinfoapp.service;

import com.kapitanovslog.dailyinfoapp.model.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    @Value("${weather-app-key}")
    private String key;

    @Override
    public Weather getWeatherByLocation(String location) {
        RestTemplate restTemplate = new RestTemplate();
        String url = API_URL + location + "&appId=" + key + "&units=metric";
        ResponseEntity<Weather> weather = restTemplate.getForEntity(url, Weather.class);
        return weather.getStatusCode() == HttpStatus.OK ? weather.getBody() : new Weather();
    }


}
