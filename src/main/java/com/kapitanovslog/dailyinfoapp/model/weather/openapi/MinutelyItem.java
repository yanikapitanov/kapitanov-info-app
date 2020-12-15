package com.kapitanovslog.dailyinfoapp.model.weather.openapi;

public class MinutelyItem{
	private int dt;
	private double precipitation;

	public void setDt(int dt){
		this.dt = dt;
	}

	public int getDt(){
		return dt;
	}

	public void setPrecipitation(double precipitation){
		this.precipitation = precipitation;
	}

	public double getPrecipitation(){
		return precipitation;
	}
}
