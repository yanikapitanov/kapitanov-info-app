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
@ConfigurationProperties(prefix = "geocode")
@ConstructorBinding
public class GeocodeConfig {
    @NotEmpty
    private final String url;

}
