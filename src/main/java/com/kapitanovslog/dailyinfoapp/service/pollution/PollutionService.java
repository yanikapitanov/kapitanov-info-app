package com.kapitanovslog.dailyinfoapp.service.pollution;

import com.kapitanovslog.dailyinfoapp.model.pollution.Pollution;

public interface PollutionService {
    Pollution getPollutionData(String location);

    String pollutionInfoToString(String location);
}
