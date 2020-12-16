package com.kapitanovslog.dailyinfoapp.model.pollution;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pollution{
	private List<ListItem> list;

	public void setList(List<ListItem> list){
		this.list = list;
	}

	private String location;

	public List<ListItem> getList(){
		return list;
	}

	public Pollution() {
	}

	public Pollution(List<ListItem> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Pollution{" +
				", list=" + list +
				'}';
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}