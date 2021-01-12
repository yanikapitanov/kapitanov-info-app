package com.kapitanovslog.dailyinfoapp.service.telegram;

import com.kapitanovslog.dailyinfoapp.service.covid.CovidService;
import com.kapitanovslog.dailyinfoapp.service.hackernews.HackerNewsService;
import com.kapitanovslog.dailyinfoapp.service.pollution.AirPollutionService;
import com.kapitanovslog.dailyinfoapp.service.transport.TelegramBotTransport;
import com.kapitanovslog.dailyinfoapp.service.weather.TelegramBotWeather;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public final class ServicesMenu {

    private final TelegramBotWeather weatherTelegram;
    private final AirPollutionService airPollutionService;
    private final CovidService covidService;
    private final HackerNewsService hackerNews;
    private final TelegramBotTransport transport;

    public ServicesMenu(TelegramBotWeather weatherTelegram, AirPollutionService airPollutionService, CovidService covidService, HackerNewsService hackerNews, TelegramBotTransport transport) {
        this.weatherTelegram = weatherTelegram;
        this.airPollutionService = airPollutionService;
        this.covidService = covidService;
        this.hackerNews = hackerNews;
        this.transport = transport;
    }

    public static final List<String> COMMANDS = Arrays.asList("/weekly", "/daily", "/pollution", "/covid", "/transport", "/help", "/hnews");

    public String getMenu(String userInput) throws InterruptedException {

        if (userInput == null || userInput.isEmpty()) {
            userInput = "/help";
        }

        String command = getCommand(userInput);
        String param = userInput.replace(command, "").trim();

        if (command.equalsIgnoreCase("/help")) {
            return "/pollution - Shows air pollution at a given location using open source data e.g. /pollution Munich\n" +
                    "/weekly - Shows weather prediction for the next 7 days for a given location e.g. /weekly London\n" +
                    "/daily - Shows weather prediction in hours for a given location e.g. /daily London\n" +
                    "/covid - Provides Covid19 info per country e.g. /covid Germany\n" +
                    "/transport - Provides disruptions public transport disruptions in Munich for a particular line e.g. /transport s1\n" +
                    "/help - Provides a help menu";
        } else if (command.equalsIgnoreCase("/weekly")) {
            return weatherTelegram.getWeeklyWeather(param);
        }else if (command.equalsIgnoreCase("/daily")) {
            return weatherTelegram.getHourlyWeather(param);
        } else if (command.equalsIgnoreCase("/pollution")) {
            return airPollutionService.pollutionInfoToString(param);
        } else if (command.equalsIgnoreCase("/covid")) {
            return covidService.getCovidInfoByCountry(param);
        } else if (command.equalsIgnoreCase("/transport")) {
            return transport.provideInterruptions(param);
        } else if (command.equalsIgnoreCase("/hnews")) {
            return hackerNews.getHackerNewsTopStoriesIds();
        }

        return "Type /help for list of commands";
    }

    private String getCommand(String userInput) {
        return COMMANDS.stream()
                .filter(userInput::contains)
                .findFirst()
                .orElse("/help");
    }

}
