package com.kapitanovslog.dailyinfoapp.services.geolocation.config;

import com.kapitanovslog.dailyinfoapp.config.GeocodeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class GeoClientConfig {

    @Bean
    RestClient geoWebClient(RestClient.Builder webClientBuilder, GeocodeConfig geocodeConfig) {
        return webClientBuilder
                .baseUrl(geocodeConfig.url())
                .build();
    }
}
