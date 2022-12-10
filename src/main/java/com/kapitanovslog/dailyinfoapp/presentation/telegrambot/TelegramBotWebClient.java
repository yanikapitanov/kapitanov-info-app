package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import com.kapitanovslog.dailyinfoapp.config.TelegramBotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
class TelegramBotWebClient {

    private final WebClient webClient;
    private final TelegramBotConfig telegramBotConfig;

    @Autowired
    TelegramBotWebClient(WebClient.Builder webClient,
                         TelegramBotConfig telegramBotConfig) {
        this.webClient = webClient.baseUrl("https://api.telegram.org").build();
        this.telegramBotConfig = telegramBotConfig;
    }

    void sendMessage(Long chatId, String text) {
        webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/bot{token}/sendMessage")
                        .queryParam("chat_id", chatId)
                        .queryParam("text", text)
                        .build(telegramBotConfig.key()))
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
