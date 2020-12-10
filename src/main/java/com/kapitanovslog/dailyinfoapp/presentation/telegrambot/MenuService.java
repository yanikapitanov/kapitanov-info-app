package com.kapitanovslog.dailyinfoapp.presentation.telegrambot;

import com.kapitanovslog.dailyinfoapp.services.covid.CovidService;
import com.kapitanovslog.dailyinfoapp.services.pollution.AirPollutionService;
import com.kapitanovslog.dailyinfoapp.services.reminder.ReminderService;
import com.kapitanovslog.dailyinfoapp.services.transport.TelegramBotTransport;
import com.kapitanovslog.dailyinfoapp.services.weather.WeatherBotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
class MenuService {


     private final WeatherBotService weatherBotService;
     private final AirPollutionService airPollutionService;
     private final CovidService covidService;
     private final TelegramBotTransport telegramBotTransport;
     private final ReminderService reminderService;

    @Autowired
    MenuService(WeatherBotService weatherBotService,
                AirPollutionService airPollutionService,
                CovidService covidService,
                TelegramBotTransport telegramBotTransport,
                ReminderService reminderService) {
        this.weatherBotService = weatherBotService;
        this.airPollutionService = airPollutionService;
        this.covidService = covidService;
        this.telegramBotTransport = telegramBotTransport;
        this.reminderService = reminderService;
    }

    String handleRequest(Long chatId, String userInput) {
        Objects.requireNonNull(userInput);
        Objects.requireNonNull(chatId);
        Command command = Command.of(userInput);

        if (command.getDetails().isBlank()) {
            return printHelpMenu();
        }

        if (command.getCommand().equalsIgnoreCase("/weekly")) {
            return weatherBotService.fetchWeeklyWeather(command.getDetails());
        }
        if (command.getCommand().equalsIgnoreCase("/daily")) {
            return weatherBotService.fetchHourlyWeather(command.getDetails());
        }
        if (command.getCommand().equalsIgnoreCase("/pollution")) {
            return airPollutionService.findAirPollutionDetails(command.getDetails());
        }
        if (command.getCommand().equalsIgnoreCase("/covid")) {
            return covidService.findCovidInfoByCountry(command.getDetails());
        }
        if (command.getCommand().equalsIgnoreCase("/transport")) {
            return telegramBotTransport.provideInterruptions(command.getDetails());
        }
        if (command.getCommand().equalsIgnoreCase("/reminder")) {
            return reminderService.storeReminder(chatId, command.getDetails());
        }
        return printHelpMenu();
    }

    public Map<Long, String> findAllRemindersBy(LocalDate reminderAt) {
        return reminderService.findAllRemindersBy(reminderAt);
    }

    private static String printHelpMenu() {
        return "/pollution - Shows air pollution at a given location using open source data e.g. /pollution Munich\n" +
                "/weekly - Shows weather prediction for the next 7 days for a given location e.g. /weekly London\n" +
                "/daily - Shows weather prediction in hours for a given location e.g. /daily London\n" +
                "/covid - Provides Covid19 info per country e.g. /covid Germany\n" +
                "/transport - Provides disruptions public transport disruptions in Munich for a particular line e.g. /transport s1\n" +
                "/help - Provides a help menu";
    }

}
