package com.kapitanovslog.dailyinfoapp.model.airpollution;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AirPollution {

    private Sensor sensor;
    private Location location;
    private String timestamp;
    @JsonProperty("sampling_rate")
    private String samplingRate;
    private long id;
    @JsonProperty("sensordatavalues")
    private List<SensorDataValues> sensorDataValues;

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSamplingRate() {
        return samplingRate;
    }

    public void setSamplingRate(String samplingRate) {
        this.samplingRate = samplingRate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<SensorDataValues> getSensorDataValues() {
        return sensorDataValues;
    }

    public void setSensorDataValues(List<SensorDataValues> sensorDataValues) {
        this.sensorDataValues = sensorDataValues;
    }
}
