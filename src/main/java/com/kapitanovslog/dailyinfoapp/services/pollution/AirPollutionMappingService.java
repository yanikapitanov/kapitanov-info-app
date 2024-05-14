package com.kapitanovslog.dailyinfoapp.services.pollution;

import com.kapitanovslog.dailyinfoapp.services.pollution.model.AirPollutionResource;
import com.kapitanovslog.dailyinfoapp.services.pollution.model.AirPollutionResponse;
import org.springframework.stereotype.Service;

@Service
class AirPollutionMappingService {


    AirPollutionResponse fetchPollutionDetails(AirPollutionResource pollutions, String areaDisplayName) {
        double pm10Avg = calculatePm10Average(pollutions);
        double pm25AVg = calculatePm25Average(pollutions);
        String pm25Quality = mapAirQualityForPM25(pm25AVg);
        String pm10Quality = mapAirQualityForPM10(pm10Avg);

        return AirPollutionResponse.builder()
                .location(areaDisplayName)
                .pm10Quality(pm10Quality)
                .pm10Value(pm10Avg)
                .pm25Quality(pm25Quality)
                .pm25value(pm25AVg)
                .build();
    }

    private double calculatePm10Average(AirPollutionResource pollutions) {
        return pollutions.items().stream()
                .map(components -> components.components().pm10())
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private double calculatePm25Average(AirPollutionResource pollutions) {
        return pollutions.items().stream()
                .map(components -> components.components().pm25())
                .reduce(Double::sum)
                .orElse(0.0);
    }

    private String mapAirQualityForPM10(double pm10FilterValue) {
        if (pm10FilterValue <= 40.0) {
            return "Good";
        }
        if (pm10FilterValue > 40.0 && pm10FilterValue <= 80.0) {
            return "Moderate";
        }
        if (pm10FilterValue > 80.0 && pm10FilterValue <= 120.0) {
            return "Poor";
        }
        if (pm10FilterValue > 120 && pm10FilterValue <= 240.0) {
            return "Very Poor";
        }
        return "Hazardous";
    }

    private String mapAirQualityForPM25(double pm25FilterValue) {
        if (pm25FilterValue <= 12.0) {
            return "Good";
        }
        if (pm25FilterValue > 12.0 && pm25FilterValue <= 35.4) {
            return "Moderate";
        }
        if (pm25FilterValue > 35.4 && pm25FilterValue <= 150.4) {
            return "Unhealthy";
        }
        if (pm25FilterValue > 150.4 && pm25FilterValue <= 250.4) {
            return "Very Unhealthy";
        }
        return "Hazardous";
    }
}
