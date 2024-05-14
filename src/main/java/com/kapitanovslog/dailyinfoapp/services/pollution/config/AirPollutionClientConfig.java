package com.kapitanovslog.dailyinfoapp.services.pollution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
class AirPollutionClientConfig {

    @Bean
    RestClient airPollutionRestClient(RestClient.Builder webClientBuilder, AirPollutionProperties airPollutionProperties) {
        return webClientBuilder
                .baseUrl(airPollutionProperties.url())
                .build();
    }

}
