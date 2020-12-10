package com.kapitanovslog.dailyinfoapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main{

	@JsonProperty("temp")
	private double temp;

	@JsonProperty("temp_min")
	private double tempMin;

	@JsonProperty("humidity")
	private int humidity;

	@JsonProperty("pressure")
	private int pressure;

	@JsonProperty("feels_like")
	private double feelsLike;

	@JsonProperty("temp_max")
	private double tempMax;

	public double getTemp(){
		return temp;
	}

	public double getTempMin(){
		return tempMin;
	}

	public int getHumidity(){
		return humidity;
	}

	public int getPressure(){
		return pressure;
	}

	public double getFeelsLike(){
		return feelsLike;
	}

	public double getTempMax(){
		return tempMax;
	}
}