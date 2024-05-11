package com.kapitanovslog.dailyinfoapp.services.weather.client;

import com.kapitanovslog.dailyinfoapp.config.WeatherConfig;
import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Log4j2
@Component
public class WeatherClient {

    private final RestClient restClient;
    private final WeatherConfig weatherConfig;

    @Autowired
    WeatherClient(RestClient weatherWebClient, WeatherConfig weatherConfig) {
        this.restClient = weatherWebClient;
        this.weatherConfig = weatherConfig;
    }

    public WeatherResponseResource fetchWeatherDetails(GeocodeLocation geocodeLocation) {
        log.info("Fetching weather details for location: {}", geocodeLocation);
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("lon", geocodeLocation.lon())
                        .queryParam("lat", geocodeLocation.lat())
                        .queryParam("appid", weatherConfig.key())
                        .queryParam("units", "metric")
                        .build())
                .retrieve()
                .body(WeatherResponseResource.class);
    }
}
