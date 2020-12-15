package com.kapitanovslog.dailyinfoapp.model.weather.location;

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

	public Coord(double lon, double lat) {
		this.lon = lon;
		this.lat = lat;
	}

	public Coord() {
	}

	@Override
	public String toString() {
		return "Coord{" +
				"lon=" + lon +
				", lat=" + lat +
				'}';
	}
}