package com.kapitanovslog.dailyinfoapp.services.weather.config;

import com.kapitanovslog.dailyinfoapp.config.WeatherConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
class WeatherClientConfig {

    @Bean
    RestClient weatherWebClient(RestClient.Builder webClientBuilder, WeatherConfig weatherConfig) {
        return webClientBuilder
                .baseUrl(weatherConfig.url())
                .build();
    }

}
