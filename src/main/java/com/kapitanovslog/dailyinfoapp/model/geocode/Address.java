package com.kapitanovslog.dailyinfoapp.model.geocode;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address{

	@JsonProperty("country")
	private String country;

	@JsonProperty("country_code")
	private String countryCode;

	@JsonProperty("city")
	private String city;

	@JsonProperty("municipality")
	private String municipality;

	@JsonProperty("postcode")
	private String postcode;

	@JsonProperty("suburb")
	private String suburb;

	@JsonProperty("state")
	private String state;

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCountryCode(String countryCode){
		this.countryCode = countryCode;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setMunicipality(String municipality){
		this.municipality = municipality;
	}

	public String getMunicipality(){
		return municipality;
	}

	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getPostcode(){
		return postcode;
	}

	public void setSuburb(String suburb){
		this.suburb = suburb;
	}

	public String getSuburb(){
		return suburb;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}
}