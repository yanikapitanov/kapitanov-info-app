package com.kapitanovslog.dailyinfoapp.exception;

public class WeatherServiceNotAvailable extends RuntimeException {

    public WeatherServiceNotAvailable(String message) {
        super(message);
    }

    public WeatherServiceNotAvailable() {
    }
}
