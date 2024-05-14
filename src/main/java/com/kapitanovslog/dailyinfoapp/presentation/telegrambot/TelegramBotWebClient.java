package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import com.kapitanovslog.dailyinfoapp.config.TelegramBotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
class TelegramBotWebClient {

    private final RestClient webClient;
    private final TelegramBotConfig telegramBotConfig;

    @Autowired
    TelegramBotWebClient(RestClient telegramWebClient,
                         TelegramBotConfig telegramBotConfig) {
        this.webClient = telegramWebClient;
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
                .toBodilessEntity();
    }
}
