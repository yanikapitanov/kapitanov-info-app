package com.kapitanovslog.dailyinfoapp.services.pollution.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AirPollutionResource(@JsonProperty("list") List<ListItem> items) {

    public record ListItem(Components components) {
    }

}
