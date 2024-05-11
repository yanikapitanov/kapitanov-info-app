package com.kapitanovslog.dailyinfoapp.services.weather.model;

public record Details(
        double temp,
        double feels_like,
        double temp_min,
        double temp_max,
        int pressure,
        int humidity) {

    @Override
    public String toString() {
        return "Temp: " + temp + "C \n" +
               "Feels like: " + feels_like + "C \n" +
               "Temp Min:" + temp_min + "C \n" +
               "Temp Max:" + temp_max + "C \n" +
               "Pressure: " + pressure + "\n" +
               "Humidity: " + humidity + "\n";
    }
}
