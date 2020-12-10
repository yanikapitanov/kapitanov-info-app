package com.kapitanovslog.dailyinfoapp.services.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
	private List<AlertsItem> alerts;
	private Current current;
	private String timezone;
	private int timezoneOffset;
	private List<DailyItem> daily;
	private Double lon;
	private List<HourlyItem> hourly;
	private Double lat;

}