package com.kapitanovslog.dailyinfoapp.services.pollution;

import com.kapitanovslog.dailyinfoapp.services.pollution.model.AirPollution;
import com.kapitanovslog.dailyinfoapp.services.pollution.model.AirPollutionResponse;
import com.kapitanovslog.dailyinfoapp.services.pollution.model.SensorDataValues;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AirPollutionMappingService {


    AirPollutionResponse fetchPollutionDetails(List<AirPollution> pollutions, String areaDisplayName) {
        double pm10Avg = calculateAverage(pollutions, "P1");
        double pm25AVg = calculateAverage(pollutions, "P2");
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

    private double calculateAverage(List<AirPollution> pollutions, String filter) {
        return pollutions.stream()
                .flatMap(airPollution -> airPollution.getSensorDataValues().stream())
                .filter(sensorDataValues -> sensorDataValues.getValueType().equalsIgnoreCase(filter))
                .mapToDouble(SensorDataValues::getValue)
                .average()
                .orElse(0);
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
