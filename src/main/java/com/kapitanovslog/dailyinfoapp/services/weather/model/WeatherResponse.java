package com.kapitanovslog.dailyinfoapp.services.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

import java.util.List;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(List<Weather> weather, String location, Details details) {

}
