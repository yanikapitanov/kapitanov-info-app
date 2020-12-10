package com.kapitanovslog.dailyinfoapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Getter
@Validated
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "telegram")
public class TelegramBotConfig {
    @NotEmpty
    private final String key;
    @NotEmpty
    private final String userName;
}
