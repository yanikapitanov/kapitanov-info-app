package com.kapitanovslog.dailyinfoapp.services.pollution.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Components(
        Double co,
        Double no,
        Double no2,
        Double o3,
        Double so2,
        @JsonProperty("pm2_5")
        Double pm25,
        Double pm10
) {

}
