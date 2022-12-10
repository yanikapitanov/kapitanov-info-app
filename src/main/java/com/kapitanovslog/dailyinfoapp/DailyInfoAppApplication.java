package com.kapitanovslog.dailyinfoapp;

import com.kapitanovslog.dailyinfoapp.config.GeocodeConfig;
import com.kapitanovslog.dailyinfoapp.config.TelegramBotConfig;
import com.kapitanovslog.dailyinfoapp.config.WeatherConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJdbcRepositories
@EnableScheduling
@EnableConfigurationProperties({
        GeocodeConfig.class,
        TelegramBotConfig.class,
        WeatherConfig.class
})
public class DailyInfoAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(DailyInfoAppApplication.class, args);

    }

}
