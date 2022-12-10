package com.kapitanovslog.dailyinfoapp.config;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "telegram")
public record TelegramBotConfig(
        @NotEmpty String key,
        @NotEmpty String userName) {
}
