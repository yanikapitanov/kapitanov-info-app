package com.kapitanovslog.dailyinfoapp.services.weather;

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
    public ResponseEntity<?> getWeather(@RequestParam(required = false) String location) {
        Map<String, Object> response = new HashMap<>();
        WeatherResponse weather = weatherService.findWeatherByLocation(location);
        response.put("hourly", weather.getHourly());
        response.put("location", weather.getLocation());
        response.put("daily", weather.getDaily());
        response.put("alerts", weather.getAlerts());
        return ResponseEntity.ok().body(response);
    }

}
