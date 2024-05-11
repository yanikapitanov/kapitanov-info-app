package com.kapitanovslog.dailyinfoapp.services.weather;

import com.kapitanovslog.dailyinfoapp.model.RequestMessage;
import com.kapitanovslog.dailyinfoapp.services.ServiceProvider;
import com.kapitanovslog.dailyinfoapp.services.weather.model.WeatherResponse;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service("/weather")
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

    private String fetchWeather(String location) {
        WeatherResponse response = weatherDataProvider(location);
        return weatherInfoProvider(response);
    }

    private WeatherResponse weatherDataProvider(String location) {
        return weatherService.findWeatherByLocation(location);
    }

    private String weatherInfoProvider(WeatherResponse response) {
        return String.format("Weather for %s %n%n", response.location()) +
                response.weather().stream()
                        .map(w -> w.description() + " " + setEmoji(w.description()) + "\n")
                        .collect(Collectors.joining()) + response.details().toString();
    }

    private static String setEmoji(String description) {
        return EMOJIS.keySet().stream()
                .filter(description::contains)
                .findFirst()
                .map(EMOJIS::get)
                .map(EmojiParser::parseToUnicode)
                .orElse("");
    }

    @Override
    public String execute(RequestMessage requestMessage) {
        return fetchWeather(requestMessage.message());
    }
}
