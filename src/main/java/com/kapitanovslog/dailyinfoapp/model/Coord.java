package com.kapitanovslog.dailyinfoapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Coord{

	@JsonProperty("lon")
	private double lon;

	@JsonProperty("lat")
	private double lat;

	public double getLon(){
		return lon;
	}

	public double getLat(){
		return lat;
	}
}