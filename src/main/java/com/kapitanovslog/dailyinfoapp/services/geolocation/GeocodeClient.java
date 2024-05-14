package com.kapitanovslog.dailyinfoapp.services.geolocation;

import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Log4j2
@Component
class GeocodeClient {

    // https://nominatim.openstreetmap.org/search?q=donnersbergerbrucke&format=json&polygon=1&addressdetails=1&type=suburb

    private final RestClient webClient;

    @Autowired
    GeocodeClient(RestClient geoWebClient) {
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
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
