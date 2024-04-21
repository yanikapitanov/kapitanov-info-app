package com.kapitanovslog.dailyinfoapp.services.weather;

import com.kapitanovslog.dailyinfoapp.model.RequestMessage;
import com.kapitanovslog.dailyinfoapp.services.ServiceProvider;
import com.kapitanovslog.dailyinfoapp.services.weather.model.TimeItem;
import com.kapitanovslog.dailyinfoapp.services.weather.model.WeatherItem;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Component
public class WeatherBotService implements ServiceProvider {

    private static final Map<String, String> EMOJIS = Map.of(
            "cloud", ":cloud:",
            "clear", ":sunny:",
            "rain", ":cloud_rain:",
            "snow", ":cloud_snow:"
    );

    private final WeatherService weatherService;

    @Autowired
    WeatherBotService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public String fetchWeeklyWeather(String location) {
        WeatherResponse response = weatherDataProvider(location);
        return weatherInfoProvider(location, response.getDaily(), 7);
    }

    public String fetchHourlyWeather(String location) {
        WeatherResponse response = weatherDataProvider(location);
        return weatherInfoProvider(location, response.getHourly(), 12);
    }

    private WeatherResponse weatherDataProvider(String location) {
        return weatherService.findWeatherByLocation(location);
    }

    private <T extends TimeItem> String weatherInfoProvider(String location, List<T> timeItems, int limit) {
        return String.format("Weather for %s %n%n", location) +
                weatherItemsDataFormatter(timeItems, limit);
    }

    private <T extends TimeItem> String weatherItemsDataFormatter(List<T> timeItems, int limit) {
        return timeItems.stream()
                .limit(limit)
                .map(this::weatherItemDataFormatter)
                .collect(Collectors.joining());
    }

    private String weatherItemDataFormatter(TimeItem timeItem) {
        return String.format("%s %s C %s%n",
                timeItem.getDateOrTime(),
                timeItem.getTemperature(),
                getWeatherResponseData(timeItem.getWeather()));
    }

    private String getWeatherResponseData(List<WeatherItem> items) {
        return items.stream()
                .map(WeatherItem::getDescription)
                .map(WeatherBotService::setEmoji)
                .collect(Collectors.joining("\n"));
    }

    public static String setEmoji(final String description) {
        return EMOJIS.keySet()
                .stream()
                .filter(description::contains)
                .findFirst()
                .map(EMOJIS::get)
                .map(EmojiParser::parseToUnicode)
                .orElse("");
    }

    @Override
    public String execute(RequestMessage requestMessage) {
        return "";
    }
}
