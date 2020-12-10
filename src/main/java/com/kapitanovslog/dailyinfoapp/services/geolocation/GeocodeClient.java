package com.kapitanovslog.dailyinfoapp.services.geolocation;

import com.kapitanovslog.dailyinfoapp.config.GeocodeConfig;
import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
class GeocodeClient {

    // https://nominatim.openstreetmap.org/search?q=donnersbergerbrucke&format=json&polygon=1&addressdetails=1&type=suburb

    private final GeocodeConfig geocodeConfig;

    private final WebClient webClient;

    @Autowired
    GeocodeClient(GeocodeConfig geocodeConfig, WebClient webClient) {
        this.geocodeConfig = geocodeConfig;
        this.webClient = webClient;
    }

    List<GeocodeLocation> findGeocodeLocation(String location) {
        String url = String.format(geocodeConfig.getUrl(), location);
        return webClient.get().uri(URI.create(url))
                .retrieve()
                .bodyToMono(GeocodeLocation[].class)
                .map(Arrays::asList)
                .block();
    }
}
