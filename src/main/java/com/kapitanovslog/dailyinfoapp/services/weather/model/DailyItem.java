package com.kapitanovslog.dailyinfoapp.services.weather.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@ToString
@EqualsAndHashCode
public class DailyItem extends TimeItem {
	private Double rain;
	private Integer sunrise;
	private Temp temp;
	private Double uvi;
	private FeelsLike feelsLike;
	private Integer pop;
	private Integer windDeg;
	private Double dewPoint;
	private Integer sunset;

	@Override
	public String getDateOrTime() {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(super.getDt()), ZoneId.systemDefault()).getDayOfWeek().name();
	}

	@Override
	public Double getTemperature() {
		return this.temp.getDay();
	}
}