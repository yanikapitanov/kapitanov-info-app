package com.kapitanovslog.dailyinfoapp.services.weather.model;

import lombok.Data;

@Data
public class Temp {
	private Double min;
	private Double max;
	private Double eve;
	private Double night;
	private Double day;
	private Double morn;

}
