package com.kapitanovslog.dailyinfoapp.service.telegram;

import com.kapitanovslog.dailyinfoapp.model.weather.WeatherResponse;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.DailyItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.HourlyItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.WeatherItem;
import com.kapitanovslog.dailyinfoapp.service.weather.WeatherService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class TelegramBotWeather {

    private final WeatherService weatherService;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TelegramBotWeather(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    public String getWeatherMessageUpdate(String location, String command) {
        StringBuilder result = new StringBuilder();

        WeatherResponse response = weatherService.getWeatherByLocation(location);
        if(response == null) {
            return "Location not found!";
        }
        result.append("Weather for ").append(response.getLocation())
                .append(", ")
                .append("\n\n");
        result.append("Current: ").append(response.getCurrent().getTemp()).append(" C").append("\n\n");

        if (command.equalsIgnoreCase("/wd")) {
            result.append(getDailyWeather(response));
        } else {
            result.append(getHourlyWeather(response));
        }
        return result.toString();
    }
    private String getDailyWeather(WeatherResponse response) {
        StringBuilder sb = new StringBuilder();
        response.getDaily()
                .forEach(dailyItem -> sb.append(createDailyWeatherData(dailyItem)));
        return sb.toString();
    }

    private String getHourlyWeather(WeatherResponse response) {
        StringBuilder sb = new StringBuilder();
        response.getHourly()
                .stream()
                .limit(10L)
                .forEach(hourlyItem -> sb.append(createHourlyWeatherData(hourlyItem)));
        return sb.toString();
    }

    private String createHourlyWeatherData(HourlyItem hourlyItem) {
        StringBuilder sb = new StringBuilder();
        Instant instant = Instant.ofEpochSecond(hourlyItem.getDt());
        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
        sb.append(date.toLocalTime().toString())
                .append(" ");
        sb.append(hourlyItem.getTemp())
                .append(" C ");
        sb.append(getWeatherResponseData(hourlyItem.getWeather()))
                .append("\n");
        return sb.toString();
    }

    private String createDailyWeatherData(DailyItem dailyItem) {
        StringBuilder sb = new StringBuilder();
        Instant instant = Instant.ofEpochSecond(dailyItem.getDt());
        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
        sb.append(date.format(formatter))
                .append(" ");
        sb.append(dailyItem.getTemp().getDay())
                .append(" C ");
        sb.append(getWeatherResponseData(dailyItem.getWeather()))
                .append("\n");
        return sb.toString();
    }

    private String getWeatherResponseData(List<WeatherItem> items) {
        StringBuilder sb = new StringBuilder();
        items.forEach(weatherItem -> sb.append(setEmoji(weatherItem.getDescription())).append("\n"));
        return sb.toString();
    }

    private String setEmoji(String description) {
        if (description.contains("cloud")) {
            return EmojiParser.parseToUnicode(":cloud:");
        } else if (description.contains("clear")) {
            return EmojiParser.parseToUnicode(":sunny:");
        } else if (description.contains("rain")) {
            return EmojiParser.parseToUnicode(":cloud_rain:");
        } else if (description.contains("snow")) {
            return EmojiParser.parseToUnicode(":cloud_snow:");
        }
        return "";
    }
}
