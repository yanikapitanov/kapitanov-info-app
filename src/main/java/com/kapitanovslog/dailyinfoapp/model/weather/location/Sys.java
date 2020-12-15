package com.kapitanovslog.dailyinfoapp.model.weather.location;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sys{

	@JsonProperty("country")
	private String country;

	@JsonProperty("sunrise")
	private int sunrise;

	@JsonProperty("sunset")
	private int sunset;

	@JsonProperty("id")
	private int id;

	@JsonProperty("type")
	private int type;

	@JsonProperty("message")
	private double message;

	public String getCountry(){
		return country;
	}

	public int getSunrise(){
		return sunrise;
	}

	public int getSunset(){
		return sunset;
	}

	public int getId(){
		return id;
	}

	public int getType(){
		return type;
	}

	public double getMessage(){
		return message;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setSunrise(int sunrise) {
		this.sunrise = sunrise;
	}

	public void setSunset(int sunset) {
		this.sunset = sunset;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setMessage(double message) {
		this.message = message;
	}
}