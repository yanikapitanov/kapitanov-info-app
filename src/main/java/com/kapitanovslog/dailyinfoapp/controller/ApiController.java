package com.kapitanovslog.dailyinfoapp.controller;

import com.kapitanovslog.dailyinfoapp.model.Weather;
import com.kapitanovslog.dailyinfoapp.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    private final WeatherService weatherService;

    public ApiController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public ResponseEntity<?> getWeather(@RequestParam String location) {
        Map<String, Object> response = new HashMap<>();
        Weather weather = weatherService.getWeatherByLocation(location);
        response.put("weather", weather.getWeather());
        response.put("main", weather.getMain());
        response.put("sys", weather.getSys());
        return ResponseEntity.ok().body(response);
    }
}
