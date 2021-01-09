package com.kapitanovslog.dailyinfoapp.model.weather.openapi;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class DailyItem extends TimeItem {
	private double rain;
	private int sunrise;
	private Temp temp;
	private double uvi;
	private FeelsLike feelsLike;
	private int pop;
	private int windDeg;
	private double dewPoint;
	private int sunset;

	public void setRain(double rain){
		this.rain = rain;
	}

	public double getRain(){
		return rain;
	}

	public void setSunrise(int sunrise){
		this.sunrise = sunrise;
	}

	public int getSunrise(){
		return sunrise;
	}

	public void setTemp(Temp temp){
		this.temp = temp;
	}

	public Temp getTemp(){
		return temp;
	}

	public void setUvi(double uvi){
		this.uvi = uvi;
	}

	public double getUvi(){
		return uvi;
	}


	public void setFeelsLike(FeelsLike feelsLike){
		this.feelsLike = feelsLike;
	}

	public FeelsLike getFeelsLike(){
		return feelsLike;
	}


	public void setPop(int pop){
		this.pop = pop;
	}

	public int getPop(){
		return pop;
	}

	public void setWindDeg(int windDeg){
		this.windDeg = windDeg;
	}

	public int getWindDeg(){
		return windDeg;
	}

	public void setDewPoint(double dewPoint){
		this.dewPoint = dewPoint;
	}

	public double getDewPoint(){
		return dewPoint;
	}

	public void setSunset(int sunset){
		this.sunset = sunset;
	}

	public int getSunset(){
		return sunset;
	}

	@Override
	public String getDateOrTime() {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(super.getDt()), ZoneId.systemDefault()).getDayOfWeek().name();
	}

	@Override
	public Double getTemperature() {
		return this.temp.getDay();
	}
}