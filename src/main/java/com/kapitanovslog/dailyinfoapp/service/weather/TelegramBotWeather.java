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

    public String getWeatherMessageUpdate(String location, String command) {

        WeatherResponse response = Optional.ofNullable(weatherService.getWeatherByLocation(location))
                .orElseThrow(() -> new WeatherServiceNotAvailable("Could not catch data info"));

        return weatherProvider(command, response);
    }

    private String weatherProvider(String command, WeatherResponse response) {
        StringBuilder result = new StringBuilder();

        result.append(String.format("Weather for %s %n%n", response.getLocation()));

        if (command.contains("weekly")) {
            result.append(getWeather(response.getDaily(), 7));
        } else {
            result.append(getWeather(response.getHourly(),12));
        }

        return result.toString();
    }

    private <T extends TimeItem> String getWeather(final List<T> response, int limit) {
        return response.parallelStream()
                .limit(limit)
                .map(this::weatherDataFormatter)
                .collect(Collectors.joining());
    }

    private String weatherDataFormatter(final TimeItem timeItem) {
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
