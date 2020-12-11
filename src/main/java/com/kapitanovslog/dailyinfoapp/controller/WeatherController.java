package com.kapitanovslog.dailyinfoapp.controller;

import com.kapitanovslog.dailyinfoapp.model.weather.Weather;
import com.kapitanovslog.dailyinfoapp.service.transport.PublicTransportService;
import com.kapitanovslog.dailyinfoapp.service.weather.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getWeather(@RequestParam String location) {
        Map<String, Object> response = new HashMap<>();
        Weather weather = weatherService.getWeatherByLocation(location);
        response.put("weather", weather.getWeather());
        response.put("main", weather.getMain());
        response.put("sys", weather.getSys());
        return ResponseEntity.ok().body(response);
    }

    // TODO: weather-prediction method

    // TODO: Air pollution API
}
