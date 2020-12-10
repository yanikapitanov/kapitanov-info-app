package com.kapitanovslog.dailyinfoapp.services.pollution.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AirPollutionResponse {

    private final String location;
    private final String pm25Quality;
    private final Double pm25value;
    private final String pm10Quality;
    private final Double pm10Value;
}
