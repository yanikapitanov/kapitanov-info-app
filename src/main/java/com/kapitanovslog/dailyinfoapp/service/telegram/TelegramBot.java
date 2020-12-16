package com.kapitanovslog.dailyinfoapp.service.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramBotWeather weatherTelegram;
    private final PollutionBot pollutionBot;

    public TelegramBot(TelegramBotWeather weatherTelegram, PollutionBot pollutionBot) {
        this.weatherTelegram = weatherTelegram;
        this.pollutionBot = pollutionBot;
    }

    @Override
    public String getBotUsername() {
        return "@kapitanov_transport_bot";
    }

    @Override
    public String getBotToken() {
        return "1480130133:AAENPdu4BqMYNz8xxOdX1sVmWgXiT_MWewM";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            String response = menu(message_text);
            SendMessage message = new SendMessage();

            message.setChatId(String.valueOf(chat_id));
            message.setText(response);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String menu(String userInput) {
        System.out.println(userInput);
        if (userInput == null || userInput.isEmpty()) {
            userInput = "help";
        }

        if (userInput.equalsIgnoreCase("help")) {
            return "Type wd {city/country} for daily forecast\n" +
                    "wh {city/country} for hourly forecast";
        }

        String response = " ";
        String command = userInput.substring(0, 2);
        String location = userInput.substring(3);

        try {
            if (command.equalsIgnoreCase("wd") ||
                    command.equalsIgnoreCase("wh")) {
                response = weatherTelegram.getWeatherMessageUpdate(location, command);
            } else if (command.equalsIgnoreCase("ap")) {
                response = pollutionBot.getPollutionInfo(location);
            }
        } catch (IndexOutOfBoundsException e) {
            response = "Type \"help\" for help menu\n";
        }
        return response;
    }


}
