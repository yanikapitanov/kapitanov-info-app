package com.kapitanovslog.dailyinfoapp.model.airpollution;

public class AirPollutionResponse {

    private String location;
    private String pm25Quality;
    private Double pm25value;
    private String pm10Quality;
    private Double pm10Value;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPm25Quality() {
        return pm25Quality;
    }

    public void setPm25Quality(String pm25Quality) {
        this.pm25Quality = pm25Quality;
    }

    public Double getPm25value() {
        return pm25value;
    }

    public void setPm25value(Double pm25value) {
        this.pm25value = pm25value;
    }

    public String getPm10Quality() {
        return pm10Quality;
    }

    public void setPm10Quality(String pm10Quality) {
        this.pm10Quality = pm10Quality;
    }

    public Double getPm10Value() {
        return pm10Value;
    }

    public void setPm10Value(Double pm10Value) {
        this.pm10Value = pm10Value;
    }
}
