package com.kapitanovslog.dailyinfoapp.config;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Validated
@ConfigurationProperties(prefix = "weather")
public record WeatherConfig(
        @NotEmpty String url,
        @NotEmpty String key) {

}
