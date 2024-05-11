package com.kapitanovslog.dailyinfoapp.services.geolocation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeocodeLocation(@JsonProperty("lon") String lon,
                              @JsonProperty("lat") String lat,
                              @JsonProperty("display_name") String displayName,
                              @JsonProperty("type") String type) {
}