package com.kapitanovslog.dailyinfoapp.service.telegram;

import com.kapitanovslog.dailyinfoapp.service.covid.CovidService;
import com.kapitanovslog.dailyinfoapp.service.pollution.AirPollutionService;
import com.kapitanovslog.dailyinfoapp.service.reddit.RedditService;
import com.kapitanovslog.dailyinfoapp.service.transport.TelegramBotTransport;
import com.kapitanovslog.dailyinfoapp.service.weather.TelegramBotWeather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final TelegramBotWeather weatherTelegram;
    private final AirPollutionService airPollutionService;
    private final CovidService covidService;
    private final RedditService redditService;
    private final TelegramBotTransport transport;

    public static final List<String> COMMANDS = Arrays.asList("/wd", "/wh", "/ap", "/covid", "/inter", "/help");

    public TelegramBot(TelegramBotWeather weatherTelegram, AirPollutionService airPollutionService, CovidService covidService, RedditService redditService, TelegramBotTransport transport) {
        this.weatherTelegram = weatherTelegram;
        this.airPollutionService = airPollutionService;
        this.covidService = covidService;
        this.redditService = redditService;
        this.transport = transport;
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
        log.info(update.getMessage().getFrom().toString());
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String response = menu(message_text);
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

    private String menu(String userInput) {

        if (userInput == null || userInput.isEmpty()) {
            userInput = "/help";
        }

        String command = getCommand(userInput);
        String param = userInput.replace(command, "").trim();

        if (command.equalsIgnoreCase("/help")) {
            return "Type /wd {city/country} for daily forecast\n" +
                    "/wh {city/country} for hourly forecast\n" +
                    "/ap {location} for air pollution stats";
        } else if (command.equalsIgnoreCase("/wd") ||
                command.equalsIgnoreCase("/wh")) {
            return weatherTelegram.getWeatherMessageUpdate(param, command);
        } else if (command.equalsIgnoreCase("/ap")) {
            return airPollutionService.pollutionInfoToString(param);
        } else if (command.equalsIgnoreCase("/covid")) {
            return covidService.getCovidInfoByCountry(param);
        } else if (command.equalsIgnoreCase("/inter")) {
            return transport.provideInterruptions(param);
        }

        return "Type /help for list of commands";
    }

    private String getCommand(String userInput) {
        return COMMANDS.stream()
                .filter(userInput::contains)
                .findFirst()
                .orElse("/help");
    }

    private PartialBotApiMethod<Message> prepareMessage(String command) {
        return null;
    }


}
