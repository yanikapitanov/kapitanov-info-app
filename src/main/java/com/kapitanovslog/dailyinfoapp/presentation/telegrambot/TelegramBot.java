package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import com.kapitanovslog.dailyinfoapp.config.TelegramBotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
class TelegramBot extends TelegramLongPollingBot {

    private final TelegramBotConfig telegramBotConfig;
    private final MenuService menuService;

    @Autowired
    TelegramBot(TelegramBotConfig telegramBotConfig,
                MenuService menuService) {
        this.telegramBotConfig = telegramBotConfig;
        this.menuService = menuService;
    }

    @Override
    public String getBotUsername() {
        return telegramBotConfig.getUserName();
    }

    @Override
    public String getBotToken() {
        return telegramBotConfig.getKey();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update == null || !update.hasMessage() || !update.getMessage().hasText()) {
            throw new IllegalArgumentException("Update cannot be 'null' or empty");
        }
        String messageText = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        String responseBody = menuService.handleRequest(chatId, messageText);
        sendMessage(chatId, responseBody);
    }

    private void sendMessage(Long chatId, String responseBody) {
        try {
            execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(responseBody).build());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
