package com.kapitanovslog.dailyinfoapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@Getter
@Validated
@AllArgsConstructor
@ConfigurationProperties(prefix = "geocode")
public class GeocodeConfig {
    private final String url;

}
