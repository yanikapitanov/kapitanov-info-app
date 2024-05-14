package com.kapitanovslog.dailyinfoapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram")
public record TelegramBotConfig(
        String key,
        String userName,
        String url) {

}
