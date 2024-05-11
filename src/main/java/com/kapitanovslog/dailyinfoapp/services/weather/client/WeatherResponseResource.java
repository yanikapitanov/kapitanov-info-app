package com.kapitanovslog.dailyinfoapp.services.weather.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kapitanovslog.dailyinfoapp.services.weather.model.Details;
import lombok.Builder;

import java.util.List;

import com.kapitanovslog.dailyinfoapp.services.weather.model.Weather;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponseResource(List<Weather> weather,
                                      @JsonProperty("main") Details details) {

}