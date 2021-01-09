package com.kapitanovslog.dailyinfoapp.model.weather.openapi;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class HourlyItem extends TimeItem {
	private Rain rain;
	private double temp;
	private int visibility;
	private double feelsLike;
	private double pop;
	private int windDeg;
	private double dewPoint;

	public void setRain(Rain rain){
		this.rain = rain;
	}

	public Rain getRain(){
		return rain;
	}

	public void setTemp(double temp){
		this.temp = temp;
	}

	public double getTemp(){
		return temp;
	}

	public void setVisibility(int visibility){
		this.visibility = visibility;
	}

	public int getVisibility(){
		return visibility;
	}

	public void setFeelsLike(double feelsLike){
		this.feelsLike = feelsLike;
	}

	public double getFeelsLike(){
		return feelsLike;
	}


	public void setPop(double pop){
		this.pop = pop;
	}

	public double getPop(){
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

	@Override
	public String getDateOrTime() {
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(super.getDt()), ZoneId.systemDefault()).toLocalTime().toString();
	}

	@Override
	public Double getTemperature() {
		return this.temp;
	}
}