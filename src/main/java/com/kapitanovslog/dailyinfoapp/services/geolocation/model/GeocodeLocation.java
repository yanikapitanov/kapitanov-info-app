package com.kapitanovslog.dailyinfoapp.services.geolocation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeocodeLocation(String lon,
                              String lat,
                              String displayName,
                              String type) {

}