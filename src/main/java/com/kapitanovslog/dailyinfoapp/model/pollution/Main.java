package com.kapitanovslog.dailyinfoapp.model.pollution;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Main{

	@JsonProperty("aqi")
	private int aqi;

	public void setAqi(int aqi){
		this.aqi = aqi;
	}

	public int getAqi(){
		return aqi;
	}

	@Override
	public String toString() {
		return "Main{" +
				"aqi=" + aqi +
				'}';
	}
}