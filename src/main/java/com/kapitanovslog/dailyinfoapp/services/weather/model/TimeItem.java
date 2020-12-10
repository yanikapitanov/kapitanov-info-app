package com.kapitanovslog.dailyinfoapp.services.weather.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public abstract class TimeItem {
    private Integer pressure;
    private Integer clouds;
    private Long dt;
    private List<WeatherItem> weather;
    private Integer humidity;
    private Double windSpeed;

    public abstract String getDateOrTime();

    public abstract Double getTemperature();
}
