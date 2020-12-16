package com.kapitanovslog.dailyinfoapp.model.pollution;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListItem{

	@JsonProperty("dt")
	private int dt;

	@JsonProperty("components")
	private Components components;

	@JsonProperty("main")
	private Main main;

	public void setDt(int dt){
		this.dt = dt;
	}

	public int getDt(){
		return dt;
	}

	public void setComponents(Components components){
		this.components = components;
	}

	public Components getComponents(){
		return components;
	}

	public void setMain(Main main){
		this.main = main;
	}

	public Main getMain(){
		return main;
	}

	@Override
	public String toString() {
		return "ListItem{" +
				"dt=" + dt +
				", components=" + components +
				", main=" + main +
				'}';
	}
}