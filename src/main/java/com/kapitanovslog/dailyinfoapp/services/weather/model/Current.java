package com.kapitanovslog.dailyinfoapp.services.weather.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public final class Current{
	private Rain rain;
	private Integer sunrise;
	private Double temp;
	private Integer visibility;
	private Double uvi;
	private Integer pressure;
	private Integer clouds;
	private Double feelsLike;
	private Integer dt;
	private Integer windDeg;
	private Double dewPoint;
	private Integer sunset;
	private List<WeatherItem> weather;
	private Integer humidity;
	private Double windSpeed;
}