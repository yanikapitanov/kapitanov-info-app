package com.kapitanovslog.dailyinfoapp.service.weather;

import com.kapitanovslog.dailyinfoapp.model.weather.WeatherResponse;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.DailyItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.HourlyItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.TimeItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.WeatherItem;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TelegramBotWeather {

    private final WeatherService weatherService;

    public static final Map<String, String> emojis = Map.of(
            "cloud", ":cloud:",
            "clear", ":sunny:",
            "rain", ":cloud_rain:",
            "snow", ":cloud_snow:"
    );

    public TelegramBotWeather(WeatherService weatherService) {
        this.weatherService = weatherService;
    }


    public String getWeatherMessageUpdate(String location, String command) {
        StringBuilder result = new StringBuilder();

        WeatherResponse response = weatherService.getWeatherByLocation(location);

        if (response == null) {
            return "Location not found!";
        }
        result.append(String.format("Weather for %s %n%n", response.getLocation()));

        if (command.equalsIgnoreCase("/wd")) {
            result.append(getWeather(response.getDaily()));
        } else {
            result.append(getWeather(response.getHourly()));
        }
        return result.toString();
    }

    private String getWeather(final List<? extends TimeItem> response) {
        return response.parallelStream()
                .limit(10)
                .map(this::weatherDataFormatter)
                .collect(Collectors.joining());
    }

    private String weatherDataFormatter(final TimeItem timeItem) {
        Instant instant = Instant.ofEpochSecond(timeItem.getDt());
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());

        String dateFormat = "";
        String temp = "";

        if (timeItem instanceof DailyItem) {
            dateFormat = dateTime.getDayOfWeek().name();
            temp = String.valueOf(((DailyItem) timeItem).getTemp().getDay());
        } else if (timeItem instanceof HourlyItem) {
            dateFormat = dateTime.toLocalTime().toString();
            temp = String.valueOf(((HourlyItem) timeItem).getTemp());
        }

        return String.format("%s %s C %s%n",
                dateFormat,
                temp,
                getWeatherResponseData(timeItem.getWeather()));
    }

    private String getWeatherResponseData(List<WeatherItem> items) {
        return items.stream()
                .map(WeatherItem::getDescription)
                .map(TelegramBotWeather::setEmoji)
                .collect(Collectors.joining("\n"));
    }

    public static String setEmoji(final String description) {
        final String key = emojis.keySet()
                .stream()
                .filter(description::contains)
                .findFirst()
                .orElse("");
        return EmojiParser.parseToUnicode(emojis.get(key));
    }

}
