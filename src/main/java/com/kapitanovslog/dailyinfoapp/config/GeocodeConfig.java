package com.kapitanovslog.dailyinfoapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@Getter
@AllArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "geocode")
public class GeocodeConfig {
    private final String url;

}
