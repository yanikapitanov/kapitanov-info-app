package com.kapitanovslog.dailyinfoapp.model.airpollution;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    private String country;
    private double longitude;
    private long id;
    @JsonProperty("exact_location")
    private int exactLocation;
    private int indoor;
    private double latitude;
    private double altitude;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getExactLocation() {
        return exactLocation;
    }

    public void setExactLocation(int exactLocation) {
        this.exactLocation = exactLocation;
    }

    public int getIndoor() {
        return indoor;
    }

    public void setIndoor(int indoor) {
        this.indoor = indoor;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}
