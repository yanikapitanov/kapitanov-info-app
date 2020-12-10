package com.kapitanovslog.dailyinfoapp.services.weather.model;

import lombok.Data;

@Data
public class MinutelyItem {
    private Integer dt;
    private Double precipitation;
}
