package com.kapitanovslog.dailyinfoapp.service.pollution;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapitanovslog.dailyinfoapp.model.airpollution.AirPollution;
import com.kapitanovslog.dailyinfoapp.model.airpollution.AirPollutionResponse;
import com.kapitanovslog.dailyinfoapp.model.airpollution.SensorDataValues;
import com.kapitanovslog.dailyinfoapp.model.geocode.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.service.geocode.GeocodeService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class AirPollutionServiceImpl implements AirPollutionService {

    public static final DecimalFormat DF = new DecimalFormat("#.00");

    public static final String URL_PREFIX = "https://data.sensor.community/airrohr/v1/filter/area=";
    public static final String URL_SUFFIX = "&type=SDS011";
    private static final ObjectMapper mapper = new ObjectMapper();

    private final GeocodeService geocodeService;

    public AirPollutionServiceImpl(GeocodeService geocodeService) {
        this.geocodeService = geocodeService;
    }

    @Override
    public String pollutionInfoToString(String location) {
        StringBuilder sb = new StringBuilder();
        AirPollutionResponse pollution = getPollutionData(location);

        sb.append(pollution.getLocation()).append("\n\n");
        sb.append("Air Quality PM25: ").append(pollution.getPm25Quality()).append("\n");
        sb.append("Air Quality PM10: ").append(pollution.getPm10Quality()).append("\n");
        sb.append("Fine particles matter: ").append(DF.format(pollution.getPm25value())).append("\n");
        sb.append("Coarse particulate matter: ").append(DF.format(pollution.getPm10Value())).append("\n");

        return sb.toString();
    }

    @Override
    public AirPollutionResponse getPollutionData(String location) {
        AirPollutionResponse response = new AirPollutionResponse();
        GeocodeLocation geolocation = geocodeService.getGeocodeLocation(location);
        int area = setRegionArea(geolocation.getType());

        String url = URL_PREFIX + geolocation.getLat() + "," + geolocation.getLon() + "," + area + URL_SUFFIX;
        JsonNode pollutions = new RestTemplate().getForObject(url, JsonNode.class);
        List<AirPollution> airPollutions = getAirPollutions(pollutions);

        double pm10Avg = getAverage(airPollutions, "P1");
        double pm25AVg = getAverage(airPollutions, "P2");
        String pm25Quality = getAirQualityForPM25(pm25AVg);
        String pm10Quality = getAirQualityForPM10(pm10Avg);

        response.setLocation(geolocation.getDisplayName());
        response.setPm10Value(pm10Avg);
        response.setPm25value(pm25AVg);
        response.setPm25Quality(pm25Quality);
        response.setPm10Quality(pm10Quality);

        return response;
    }

    private int setRegionArea(String location) {
        if (location.equalsIgnoreCase("suburb")) {
            return 1;
        } else if (location.equalsIgnoreCase("town")) {
            return 15;
        } else if (location.equalsIgnoreCase("administrative")) {
            return 300;
        }
        return 1;
    }

    private Double getAverage(List<AirPollution> pollutions, final String filter) {
        return pollutions
                .stream()
                .flatMap(ap -> ap.getSensorDataValues().stream())
                .filter(sdv -> sdv.getValueType().equalsIgnoreCase(filter))
                .mapToDouble(SensorDataValues::getValue)
                .average().orElse(0);
    }

    private List<AirPollution> getAirPollutions(JsonNode pollutions) {
        return mapper.convertValue(
                pollutions,
                new TypeReference<List<AirPollution>>() {
                }
        );
    }

    private String getAirQualityForPM10(Double p1) {
        if (p1 <= 40.0) {
            return "Good";
        } else if (p1 > 40.0 && p1 <= 80.0) {
            return "Moderate";
        } else if (p1 > 80.0 && p1 <= 120.0) {
            return "Poor";
        } else if (p1 > 120 && p1 <= 240.0) {
            return "Very Poor";
        } else {
            return "Hazardous";
        }
    }

    private String getAirQualityForPM25(Double p2) {
        if (p2 <= 12.0) {
            return "Good";
        } else if (p2 > 12.0 && p2 <= 35.4) {
            return "Moderate";
        } else if (p2 > 35.4 && p2 <= 150.4) {
            return "Unhealthy";
        } else if (p2 > 150.4 && p2 <= 250.4) {
            return "Very Unhealthy";
        } else {
            return "Hazardous";
        }
    }
}
