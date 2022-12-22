package com.kapitanovslog.dailyinfoapp.services.pollution;

import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.services.pollution.model.AirPollution;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Component
class AirPollutionClient {

    public static final String URL_PREFIX = "https://data.sensor.community/airrohr/v1/filter/area=";
    public static final String URL_SUFFIX = "&type=SDS011";
    private final WebClient webClient;


    AirPollutionClient(WebClient webClient) {
        this.webClient = webClient;
    }

    List<AirPollution> fetchAirPollutionDetails(GeocodeLocation geoLocation, int area) {
        return webClient.get()
                .uri(URI.create(URL_PREFIX + geoLocation.getLat() + "," + geoLocation.getLon() + "," + area + URL_SUFFIX))
                .retrieve()
                .bodyToMono(AirPollution[].class)
                .map(Arrays::asList)
                .blockOptional()
                .orElseGet(List::of);
    }
}
