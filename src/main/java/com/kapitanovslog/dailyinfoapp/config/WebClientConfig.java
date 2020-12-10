package com.kapitanovslog.dailyinfoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    WebClient.Builder webClientBuilder() {
        final int size = 16 * 1024 * 1024;
        return WebClient.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size));
    }

    @Bean
    WebClient webClient() {
        final int size = 16 * 1024 * 1024;
        return WebClient.builder()
                .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(size))
                .build();
    }
}
