package com.kapitanovslog.dailyinfoapp.service.telegram;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final ServicesMenu servicesMenu;

    public TelegramBot(ServicesMenu servicesMenu) {
        this.servicesMenu = servicesMenu;
    }

    @Override
    public String getBotUsername() {
        return "@kapitanov_transport_bot";
    }

    @Override
    public String getBotToken() {
        return "1480130133:AAENPdu4BqMYNz8xxOdX1sVmWgXiT_MWewM";
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        log.info(update.getMessage().getFrom().toString());
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String response = servicesMenu.getMenu(message_text);
            sendMessage(chat_id, response);
        }
    }

    private void sendMessage(long chat_id, String text) {
        SendMessage message = new SendMessage();
        try {
            message.setChatId(String.valueOf(chat_id));
            message.setText(text);
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
