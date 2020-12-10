package com.kapitanovslog.dailyinfoapp.services.weather.model;

import lombok.Data;

@Data
public final class AlertsItem {
    private Integer start;
    private String description;
    private String senderName;
    private Integer end;
    private String event;

}
