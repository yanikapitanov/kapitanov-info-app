package com.kapitanovslog.dailyinfoapp.services.weather.model;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
public class HourlyItem extends TimeItem {
	private Rain rain;
	private Double temp;
	private Integer visibility;
	private Double feelsLike;
	private Double pop;
	private Integer windDeg;
	private Double dewPoint;

	@Override
	public String getDateOrTime() {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(super.getDt()), ZoneId.systemDefault()).toLocalTime().toString();
	}

	@Override
	public Double getTemperature() {
		return this.temp;
	}
}