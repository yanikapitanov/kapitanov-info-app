package com.kapitanovslog.dailyinfoapp.services.geolocation;

import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Log4j2
@Component
class GeocodeClient {

    // https://nominatim.openstreetmap.org/search?q=donnersbergerbrucke&format=json&polygon=1&addressdetails=1&type=suburb

    private final WebClient webClient;

    @Autowired
    GeocodeClient(WebClient geoWebClient) {
        this.webClient = geoWebClient;
    }

    //?q=%s&format=json&polygon=1&addressdetails=1&type=suburb
    List<GeocodeLocation> findGeocodeLocation(String location) {
        log.info("Fetching geocode location for location: {}", location);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", location)
                        .queryParam("format", "json")
                        .queryParam("polygon", 1)
                        .queryParam("addressdetails", 1)
                        .queryParam("type", "suburb")
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> clientResponse.bodyToMono(String.class)
                        .map(body -> new RuntimeException("Error: " + body)))
                .bodyToMono(GeocodeLocation[].class)
                .log()
                .map(Arrays::asList)
                .block();
    }
}
