package com.kapitanovslog.dailyinfoapp.model.geocode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeLocation{

	@JsonProperty("osm_id")
	private long osmId;

	@JsonProperty("licence")
	private String licence;

	@JsonProperty("boundingbox")
	private List<String> boundingbox;

	@JsonProperty("address")
	private Address address;

	@JsonProperty("importance")
	private double importance;

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("lon")
	private String lon;

	@JsonProperty("display_name")
	private String displayName;

	@JsonProperty("type")
	private String type;

	@JsonProperty("osm_type")
	private String osmType;

	@JsonProperty("class")
	private String jsonMemberClass;

	@JsonProperty("place_id")
	private int placeId;

	@JsonProperty("lat")
	private String lat;

	public void setOsmId(long osmId){
		this.osmId = osmId;
	}

	public long getOsmId(){
		return osmId;
	}

	public void setLicence(String licence){
		this.licence = licence;
	}

	public String getLicence(){
		return licence;
	}

	public void setBoundingbox(List<String> boundingbox){
		this.boundingbox = boundingbox;
	}

	public List<String> getBoundingbox(){
		return boundingbox;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setImportance(double importance){
		this.importance = importance;
	}

	public double getImportance(){
		return importance;
	}

	public void setIcon(String icon){
		this.icon = icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setLon(String lon){
		this.lon = lon;
	}

	public String getLon(){
		return lon;
	}

	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}

	public String getDisplayName(){
		return displayName;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setOsmType(String osmType){
		this.osmType = osmType;
	}

	public String getOsmType(){
		return osmType;
	}

	public void setJsonMemberClass(String jsonMemberClass){
		this.jsonMemberClass = jsonMemberClass;
	}

	public String getJsonMemberClass(){
		return jsonMemberClass;
	}

	public void setPlaceId(int placeId){
		this.placeId = placeId;
	}

	public int getPlaceId(){
		return placeId;
	}

	public void setLat(String lat){
		this.lat = lat;
	}

	public String getLat(){
		return lat;
	}

	@Override
	public String toString() {
		return "GeocodeLocation{" +
				"lon='" + lon + '\'' +
				", displayName='" + displayName + '\'' +
				", lat='" + lat + '\'' +
				'}';
	}
}