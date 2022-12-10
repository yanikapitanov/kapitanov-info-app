package com.kapitanovslog.dailyinfoapp.services.weather;

import com.kapitanovslog.dailyinfoapp.services.geolocation.GeocodeService;
import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.services.weather.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
class WeatherService {

    private final GeocodeService geocodeService;
    private final WeatherClient weatherClient;

    @Autowired
    WeatherService(GeocodeService geocodeService, WeatherClient weatherClient) {
        this.geocodeService = geocodeService;
        this.weatherClient = weatherClient;
    }

    WeatherResponse findWeatherByLocation(String location) {
        GeocodeLocation geocodeLocation = geocodeService.findGeoLocation(location)
                .orElseThrow(() -> new IllegalStateException("Could not find geo location"));
        Response response = weatherClient.fetchWeatherDetails(geocodeLocation)
                .orElseThrow(() -> new IllegalStateException("Could not find geo location"));

        return WeatherResponse.builder()
                .location(geocodeLocation.displayName())
                .alerts(response.getAlerts())
                .daily(response.getDaily())
                .hourly(response.getHourly())
                .current(response.getCurrent())
                .build();
    }

}
