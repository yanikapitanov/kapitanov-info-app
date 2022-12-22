package com.kapitanovslog.dailyinfoapp.services.geolocation.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeLocation {
    private final String lon;
    private final String lat;
    private final String displayName;
    private final String type;

    @JsonCreator
    GeocodeLocation(@JsonProperty("lon") String lon,
                    @JsonProperty("lat") String lat,
                    @JsonProperty("display_name") String displayName,
                    @JsonProperty("type") String type) {
        this.lon = lon;
        this.lat = lat;
        this.displayName = displayName;
        this.type = type;
    }
}