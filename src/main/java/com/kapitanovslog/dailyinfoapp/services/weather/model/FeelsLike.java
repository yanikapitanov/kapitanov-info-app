package com.kapitanovslog.dailyinfoapp.services.weather.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class FeelsLike {
    private Double eve;
    private Double night;
    private Double day;
    private Double morn;
}
