package com.kapitanovslog.dailyinfoapp.model.weather.openapi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
	private List<AlertsItem> alerts;
	private Current current;
	private String timezone;
	private int timezoneOffset;
	private List<DailyItem> daily;
	private double lon;
	private List<HourlyItem> hourly;
	private List<MinutelyItem> minutely;
	private double lat;

	public void setAlerts(List<AlertsItem> alerts){
		this.alerts = alerts;
	}

	public List<AlertsItem> getAlerts(){
		return this.alerts != null ? this.alerts : new ArrayList<>();
	}

	public void setCurrent(Current current){
		this.current = current;
	}

	public Current getCurrent(){
		return current;
	}

	public void setTimezone(String timezone){
		this.timezone = timezone;
	}

	public String getTimezone(){
		return timezone;
	}

	public void setTimezoneOffset(int timezoneOffset){
		this.timezoneOffset = timezoneOffset;
	}

	public int getTimezoneOffset(){
		return timezoneOffset;
	}

	public void setDaily(List<DailyItem> daily){
		this.daily = daily;
	}

	public List<DailyItem> getDaily(){
		return daily;
	}

	public void setLon(double lon){
		this.lon = lon;
	}

	public double getLon(){
		return lon;
	}

	public void setHourly(List<HourlyItem> hourly){
		this.hourly = hourly;
	}

	public List<HourlyItem> getHourly(){
		return hourly;
	}

	public void setMinutely(List<MinutelyItem> minutely){
		this.minutely = minutely;
	}

	public List<MinutelyItem> getMinutely(){
		return minutely;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}

	@Override
	public String toString() {
		return "Response{" +
				"alerts=" + alerts +
				", current=" + current +
				", timezone='" + timezone + '\'' +
				", timezoneOffset=" + timezoneOffset +
				", daily=" + daily +
				", lon=" + lon +
				", hourly=" + hourly +
				", minutely=" + minutely +
				", lat=" + lat +
				'}';
	}
}