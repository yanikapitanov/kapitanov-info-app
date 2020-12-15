package com.kapitanovslog.dailyinfoapp.model.weather.openapi;

public class AlertsItem{
	private int start;
	private String description;
	private String senderName;
	private int end;
	private String event;

	public void setStart(int start){
		this.start = start;
	}

	public int getStart(){
		return start;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setSenderName(String senderName){
		this.senderName = senderName;
	}

	public String getSenderName(){
		return senderName;
	}

	public void setEnd(int end){
		this.end = end;
	}

	public int getEnd(){
		return end;
	}

	public void setEvent(String event){
		this.event = event;
	}

	public String getEvent(){
		return event;
	}
}
