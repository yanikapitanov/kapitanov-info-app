package com.kapitanovslog.dailyinfoapp.services.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kapitanovslog.dailyinfoapp.services.weather.model.AlertsItem;
import com.kapitanovslog.dailyinfoapp.services.weather.model.Current;
import com.kapitanovslog.dailyinfoapp.services.weather.model.DailyItem;
import com.kapitanovslog.dailyinfoapp.services.weather.model.HourlyItem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    private Current current;
    private String location;
    private List<HourlyItem> hourly;
    private List<DailyItem> daily;
    private List<AlertsItem> alerts;
}
