package com.kapitanovslog.dailyinfoapp;

import com.kapitanovslog.dailyinfoapp.config.GeocodeConfig;
import com.kapitanovslog.dailyinfoapp.config.TelegramBotConfig;
import com.kapitanovslog.dailyinfoapp.config.WeatherConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
@EnableJdbcRepositories
@EnableScheduling
@EnableConfigurationProperties({
        GeocodeConfig.class,
        TelegramBotConfig.class,
        WeatherConfig.class
})
public class DailyInfoAppApplication {

    public static void main(String[] args) throws TelegramApiException {

        ConfigurableApplicationContext ctx = SpringApplication.run(DailyInfoAppApplication.class, args);
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(ctx.getBean("infoBot", TelegramLongPollingBot.class));
    }

}
