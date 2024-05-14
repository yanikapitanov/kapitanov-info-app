package com.kapitanovslog.dailyinfoapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "weather")
public record WeatherConfig(@NotBlank String url, @NotBlank String key) {

}
