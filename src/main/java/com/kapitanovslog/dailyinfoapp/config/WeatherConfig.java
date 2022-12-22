package com.kapitanovslog.dailyinfoapp.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "weather")
public final class WeatherConfig {


    private final String url;
    private final String key;

    public WeatherConfig(String url, String key) {
        this.url = url;
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || obj != null && obj.getClass() == this.getClass();
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public String toString() {
        return "WeatherConfig[]";
    }
}
