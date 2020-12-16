package com.kapitanovslog.dailyinfoapp.service.pollution;

import com.kapitanovslog.dailyinfoapp.model.geocode.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.model.pollution.Pollution;
import com.kapitanovslog.dailyinfoapp.service.geocode.GeocodeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
