package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
class TelegramCronService {

    private final MenuService menuService;
    private final TelegramBotWebClient telegramBotWebClient;

    @Autowired
    TelegramCronService(MenuService menuService, TelegramBotWebClient telegramBotWebClient) {
        this.menuService = menuService;
        this.telegramBotWebClient = telegramBotWebClient;
    }

    @Scheduled(cron = "00 00 08 * * ?", zone = "Europe/Berlin")
    void sendReminder() {
        LocalDate today = LocalDate.now();
        Map<Long, String> remindersByChatId = menuService.findAllRemindersBy(today);
        remindersByChatId
                .keySet()
                .forEach(chatId -> telegramBotWebClient.sendMessage(chatId, remindersByChatId.get(chatId)));
    }
}
