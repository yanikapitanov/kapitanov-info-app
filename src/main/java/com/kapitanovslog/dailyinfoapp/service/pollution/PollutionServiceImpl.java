package com.kapitanovslog.dailyinfoapp.service.pollution;

import com.kapitanovslog.dailyinfoapp.model.geocode.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.model.pollution.ListItem;
import com.kapitanovslog.dailyinfoapp.model.pollution.Pollution;
import com.kapitanovslog.dailyinfoapp.service.geocode.GeocodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PollutionServiceImpl implements PollutionService {

    @Value("${weather-app-key}")
    private String key;

    public static final String POLLUTION_URL = "http://api.openweathermap.org/data/2.5/air_pollution?";

    private final GeocodeService geocodeService;

    public PollutionServiceImpl(GeocodeService geocodeService) {
        this.geocodeService = geocodeService;
    }

    @Override
    public Pollution getPollutionData(String location) {
        GeocodeLocation coordinates = geocodeService.getGeocodeLocation(location);
        String url = POLLUTION_URL + "lat=" + coordinates.getLat() + "&lon=" + coordinates.getLon() + "&appId=" + key;
        Pollution pollution = new RestTemplate().getForObject(url, Pollution.class);
        pollution.setLocation(coordinates.getDisplayName());
        return pollution;
    }

    @Override
    public String pollutionInfoToString(String location) {
        Pollution pollution = getPollutionData(location);
        StringBuilder sb = new StringBuilder();
        List<ListItem> pollutionItems = pollution.getList();
        sb.append(pollution.getLocation()).append("\n\n");
        pollutionItems.forEach(p -> {
            int aqi = p.getMain().getAqi();
            sb.append("Air Quality: ").append(setAirQuality(aqi)).append("\n");
            sb.append("Fine particles matter: ").append(p.getComponents().getPm25()).append("\n");
            sb.append("Coarse particulate matter: ").append(p.getComponents().getPm10()).append("\n");
        });
        return sb.toString();
    }

    private String setAirQuality(int aqi) {
        switch (aqi) {
            case 1:
                return "Good";
            case 2:
                return "Fair";
            case 3:
                return "Moderate";
            case 4:
                return "Poor";
            case 5:
                return "Very poor";
            default:
                return "No data";
        }
    }
}
