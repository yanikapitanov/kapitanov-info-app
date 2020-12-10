package com.kapitanovslog.dailyinfoapp.services.pollution.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class SensorDataValues {

    @JsonProperty("value_type")
    private final String valueType;
    private final long id;
    private final double value;

    @JsonCreator
    public SensorDataValues(@JsonProperty("value_type") String valueType,
                            @JsonProperty("id") long id,
                            @JsonProperty("value ")double value) {
        this.valueType = valueType;
        this.id = id;
        this.value = value;
    }
}
