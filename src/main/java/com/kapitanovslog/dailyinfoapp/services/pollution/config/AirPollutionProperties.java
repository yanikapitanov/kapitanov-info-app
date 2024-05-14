package com.kapitanovslog.dailyinfoapp.services.pollution.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import jakarta.validation.constraints.NotBlank;

@ConfigurationProperties(prefix = "air-pollution")
public record AirPollutionProperties(@NotBlank String url, @NotBlank String key) {

}
