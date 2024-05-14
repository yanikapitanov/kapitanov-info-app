package com.kapitanovslog.dailyinfoapp.presentation.telegrambot.config;

import com.kapitanovslog.dailyinfoapp.config.TelegramBotConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
class TelegramClientConfig {

    @Bean
    RestClient telegramWebClient(RestClient.Builder webClientBuilder, TelegramBotConfig telegramBotConfig) {
        return webClientBuilder
                .baseUrl(telegramBotConfig.url())
                .build();
    }

}
