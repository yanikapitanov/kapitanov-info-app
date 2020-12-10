package com.kapitanovslog.dailyinfoapp.services.pollution.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirPollution {
    @JsonProperty("sensordatavalues")
    private List<SensorDataValues> sensorDataValues;

}
