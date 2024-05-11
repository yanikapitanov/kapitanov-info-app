package com.kapitanovslog.dailyinfoapp.services.geolocation.config;

import com.kapitanovslog.dailyinfoapp.config.GeocodeConfig;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class GeoClientConfig {

    private static final int CONNECTION_TIMEOUT_SECONDS = 5;

    @Bean
    WebClient geoWebClient(WebClient.Builder webClientBuilder, GeocodeConfig geocodeConfig) {
        return webClientBuilder
                .baseUrl(geocodeConfig.url())
                .clientConnector(new ReactorClientHttpConnector(createHttpClient()))
                .build();
    }

    private HttpClient createHttpClient() {
        return HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECTION_TIMEOUT_SECONDS * 1000)
                .option(ChannelOption.SO_KEEPALIVE, false)
                .doOnConnected(connection -> connection
                        .addHandlerLast(new ReadTimeoutHandler(CONNECTION_TIMEOUT_SECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(CONNECTION_TIMEOUT_SECONDS))
                );
    }
}
