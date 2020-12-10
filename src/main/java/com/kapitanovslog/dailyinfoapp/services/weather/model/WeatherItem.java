package com.kapitanovslog.dailyinfoapp.services.weather.model;

import lombok.Data;

@Data
public class WeatherItem {
	private String icon;
	private String description;
	private String main;
	private Integer id;

}
