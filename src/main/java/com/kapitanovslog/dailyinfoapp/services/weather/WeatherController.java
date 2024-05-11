package com.kapitanovslog.dailyinfoapp.services.weather;

import com.kapitanovslog.dailyinfoapp.services.weather.model.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/weather")
class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getWeather(@RequestParam(required = false) String location) {
        WeatherResponse weather = weatherService.findWeatherByLocation(location);
        return ResponseEntity.ok().body(weather);
    }

}
