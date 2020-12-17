package com.kapitanovslog.dailyinfoapp.model.airpollution;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SensorDataValues {

    @JsonProperty("value_type")
    private String valueType;
    private long id;
    private double value;

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
