package com.kapitanovslog.dailyinfoapp.model.weather.location;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather{

	@JsonProperty("visibility")
	private int visibility;

	@JsonProperty("timezone")
	private int timezone;

	@JsonProperty("main")
	private Main main;

	@JsonProperty("clouds")
	private Clouds clouds;

	@JsonProperty("sys")
	private Sys sys;

	@JsonProperty("dt")
	private int dt;

	@JsonProperty("coord")
	private Coord coord;

	@JsonProperty("weather")
	private List<WeatherItem> weather;

	@JsonProperty("name")
	private String name;

	@JsonProperty("cod")
	private int cod;

	@JsonProperty("id")
	private int id;

	@JsonProperty("base")
	private String base;

	@JsonProperty("wind")
	private Wind wind;

	public int getVisibility(){
		return visibility;
	}

	public int getTimezone(){
		return timezone;
	}

	public Main getMain(){
		return main;
	}

	public Clouds getClouds(){
		return clouds;
	}

	public Sys getSys(){
		return sys;
	}

	public int getDt(){
		return dt;
	}

	public Coord getCoord(){
		return coord;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public String getName(){
		return name;
	}

	public int getCod(){
		return cod;
	}

	public int getId(){
		return id;
	}

	public String getBase(){
		return base;
	}

	public Wind getWind(){
		return wind;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public void setTimezone(int timezone) {
		this.timezone = timezone;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public void setClouds(Clouds clouds) {
		this.clouds = clouds;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

	public void setCoord(Coord coord) {
		this.coord = coord;
	}

	public void setWeather(List<WeatherItem> weather) {
		this.weather = weather;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}
}