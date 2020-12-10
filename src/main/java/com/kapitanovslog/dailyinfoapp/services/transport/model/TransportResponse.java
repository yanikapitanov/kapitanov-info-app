package com.kapitanovslog.dailyinfoapp.services.transport.model;

import lombok.Data;

import java.util.List;

@Data
public class TransportResponse {

    private Duration duration;
    private String text;
    private String title;
    private List<Line> lines;

}
