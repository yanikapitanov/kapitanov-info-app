package com.kapitanovslog.dailyinfoapp.services.weather;

import com.kapitanovslog.dailyinfoapp.config.WeatherConfig;
import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.services.weather.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Optional;

@Component
class WeatherClient {

    private final WebClient webClient;
    private final WeatherConfig weatherConfig;

    @Autowired
    WeatherClient(WebClient webClient, WeatherConfig weatherConfig) {
        this.webClient = webClient;
        this.weatherConfig = weatherConfig;
    }

    Optional<Response> fetchWeatherDetails(GeocodeLocation geocodeLocation) {
        String url = weatherConfig.url().formatted(geocodeLocation.lat(), geocodeLocation.lon(), weatherConfig.key());
        return webClient.get()
                .uri(URI.create(url))
                .retrieve()
                .bodyToMono(Response.class)
                .blockOptional();
    }
}
