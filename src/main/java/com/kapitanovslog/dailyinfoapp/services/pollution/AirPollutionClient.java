package com.kapitanovslog.dailyinfoapp.services.pollution;

import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.services.pollution.config.AirPollutionProperties;
import com.kapitanovslog.dailyinfoapp.services.pollution.model.AirPollutionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
class AirPollutionClient {

    private final RestClient webClient;
    private final AirPollutionProperties airPollutionProperties;

    @Autowired
    AirPollutionClient(RestClient airPollutionRestClient, AirPollutionProperties airPollutionProperties) {
        this.webClient = airPollutionRestClient;
        this.airPollutionProperties = airPollutionProperties;
    }

    AirPollutionResource fetchAirPollutionDetails(GeocodeLocation geoLocation) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("lat", geoLocation.lat())
                        .queryParam("lon", geoLocation.lon())
                        .queryParam("appId", airPollutionProperties.key())
                        .build())
                .retrieve()
                .body(AirPollutionResource.class);
    }
}
