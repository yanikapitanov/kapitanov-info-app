package com.kapitanovslog.dailyinfoapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties(prefix = "telegram")
public final class TelegramBotConfig {

    private final String key;
    private final String userName;

    TelegramBotConfig(
            String key,
            String userName) {
        this.key = key;
        this.userName = userName;
    }

    public String key() {
        return key;
    }

    public String userName() {
        return userName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (TelegramBotConfig) obj;
        return Objects.equals(this.key, that.key) &&
                Objects.equals(this.userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, userName);
    }

    @Override
    public String toString() {
        return "TelegramBotConfig[" +
                "key=" + key + ", " +
                "userName=" + userName + ']';
    }

}
