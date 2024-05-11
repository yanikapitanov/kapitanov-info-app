package com.kapitanovslog.dailyinfoapp.services.weather;

import com.kapitanovslog.dailyinfoapp.services.geolocation.GeocodeService;
import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.services.weather.client.WeatherClient;
import com.kapitanovslog.dailyinfoapp.services.weather.client.WeatherResponseResource;
import com.kapitanovslog.dailyinfoapp.services.weather.model.WeatherResponse;
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
        GeocodeLocation geocodeLocation = geocodeService.findGeoLocation(location);
        WeatherResponseResource response = weatherClient.fetchWeatherDetails(geocodeLocation);

        return WeatherResponse.builder()
                .location(geocodeLocation.displayName())
                .weather(response.weather())
                .details(response.details())
                .build();
    }

}
