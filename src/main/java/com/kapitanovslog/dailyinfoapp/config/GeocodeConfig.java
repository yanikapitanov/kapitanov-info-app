package com.kapitanovslog.dailyinfoapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "geocode")
public record GeocodeConfig(@NotEmpty String url) {

}
