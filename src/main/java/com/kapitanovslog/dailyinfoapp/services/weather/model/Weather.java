package com.kapitanovslog.dailyinfoapp.services.weather.model;

public record Weather(
        String description,
        String icon,
        int id,
        String main) {

}
