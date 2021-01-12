package com.kapitanovslog.dailyinfoapp.service.weather;

import com.kapitanovslog.dailyinfoapp.exception.WeatherServiceNotAvailable;
import com.kapitanovslog.dailyinfoapp.model.weather.WeatherResponse;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.TimeItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.WeatherItem;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class TelegramBotWeather {

    public static final Map<String, String> emojis = Map.of(
            "cloud", ":cloud:",
            "clear", ":sunny:",
            "rain", ":cloud_rain:",
            "snow", ":cloud_snow:"
    );

    private final WeatherService weatherService;

    public TelegramBotWeather(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public String getWeeklyWeather(String location) {
        WeatherResponse response = weatherDataProvider(location);
        return weatherInfoProvider(location, response.getDaily(), 7);
    }

    public String getHourlyWeather(String location) {
        WeatherResponse response = weatherDataProvider(location);
        return weatherInfoProvider(location, response.getHourly(), 12);
    }

    private WeatherResponse weatherDataProvider(String location) {
        return Optional.ofNullable(weatherService.getWeatherByLocation(location))
                .orElseThrow(() -> new WeatherServiceNotAvailable("Could not fetch data info"));
    }

    private <T extends TimeItem> String weatherInfoProvider(String location, final List<T> timeItems, int limit) {
        return String.format("Weather for %s %n%n", location) +
                weatherItemsDataFormatter(timeItems, limit);
    }

    private <T extends TimeItem> String weatherItemsDataFormatter(final List<T> timeItems, int limit) {
        return timeItems.stream()
                .limit(limit)
                .map(this::weatherItemDataFormatter)
                .collect(Collectors.joining());
    }

    private String weatherItemDataFormatter(final TimeItem timeItem) {
        return String.format("%s %s C %s%n",
                timeItem.getDateOrTime(),
                timeItem.getTemperature(),
                getWeatherResponseData(timeItem.getWeather()));
    }

    private String getWeatherResponseData(final List<WeatherItem> items) {
        return items.stream()
                .map(WeatherItem::getDescription)
                .map(TelegramBotWeather::setEmoji)
                .collect(Collectors.joining("\n"));
    }

    public static String setEmoji(final String description) {
        return emojis.keySet()
                .stream()
                .filter(description::contains)
                .findFirst()
                .map(emojis::get)
                .map(EmojiParser::parseToUnicode)
                .orElse("");
    }

}
