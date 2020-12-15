package com.kapitanovslog.dailyinfoapp.service.telegram;

import com.kapitanovslog.dailyinfoapp.model.weather.WeatherResponse;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.DailyItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.HourlyItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.TimeItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.WeatherItem;
import com.kapitanovslog.dailyinfoapp.service.weather.WeatherService;
import com.vdurmont.emoji.Emoji;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final WeatherService weatherService;
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TelegramBot(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    public String getBotUsername() {
        return "KapitanovTransportBot";
    }

    @Override
    public String getBotToken() {
        return "1480130133:AAENPdu4BqMYNz8xxOdX1sVmWgXiT_MWewM";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String[] msg_txt = message_text.split("\\s+");
            try {
                message_text = getWeatherMessageUpdate(msg_txt);
            } catch (IndexOutOfBoundsException e) {
                message_text = "The input should be weather {location} {h/d}\n";
                message_text += "h for hourly, d for daily";
            }
            SendMessage message = new SendMessage(); // Create a message object object
            message.setChatId(String.valueOf(chat_id));
            message.setText(message_text);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String getWeatherMessageUpdate(String[] userInput) {
        StringBuilder result = new StringBuilder();
        String city = userInput[1];
        String command = userInput[0];
        WeatherResponse response = weatherService.getWeatherByLocation(city);
        result.append("Weather for ").append(StringUtils.capitalize(response.getLocation()))
                .append(", ")
                .append(response.getCountry())
                .append("\n\n");
        result.append("Current: ").append(response.getCurrent().getTemp()).append(" C").append("\n\n");

        if (command.equalsIgnoreCase("wd")) {
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

    private String createHourlyWeatherData(TimeItem item) {
        StringBuilder sb = new StringBuilder();
        if (item instanceof HourlyItem) {
            HourlyItem hourlyItem = (HourlyItem) item;
            Instant instant = Instant.ofEpochSecond(hourlyItem.getDt());
            LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
            sb.append(date.toLocalTime().toString())
                    .append(" - ");
            sb.append(hourlyItem.getTemp())
                    .append(" C - ");
            sb.append(getWeatherResponseData(hourlyItem.getWeather()))
                    .append("\n");
        }
        return sb.toString();
    }

    private String createDailyWeatherData(DailyItem dailyItem) {
        StringBuilder sb = new StringBuilder();
        Instant instant = Instant.ofEpochSecond(dailyItem.getDt());
        LocalDateTime date = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
        sb.append(date.format(formatter))
                .append(" - ");
        sb.append(dailyItem.getTemp().getDay())
                .append(" C - ");
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
