package com.kapitanovslog.dailyinfoapp.service.pollution;

import com.kapitanovslog.dailyinfoapp.model.airpollution.AirPollutionResponse;

public interface AirPollutionService {
    String pollutionInfoToString(String location);

    AirPollutionResponse getPollutionData(String location);
}
