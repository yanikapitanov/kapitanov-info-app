package com.kapitanovslog.dailyinfoapp.service.weather;

import com.kapitanovslog.dailyinfoapp.model.geocode.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.model.weather.WeatherResponse;
import com.kapitanovslog.dailyinfoapp.model.weather.location.Coord;
import com.kapitanovslog.dailyinfoapp.model.weather.location.Weather;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.Response;
import com.kapitanovslog.dailyinfoapp.service.geocode.GeocodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherServiceImpl implements WeatherService {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

    private static final String LOCATION_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String OPEN_API_URL = "https://api.openweathermap.org/data/2.5/onecall";

    private final GeocodeService geocodeService;


    @Value("${weather-app-key}")
    private String key;

    public WeatherServiceImpl(GeocodeService geocodeService) {
        this.geocodeService = geocodeService;
    }

    @Override
    public WeatherResponse getWeatherByLocation(String location) {

        RestTemplate restTemplate = new RestTemplate();
        GeocodeLocation geocodeLocation = geocodeService.getGeocodeLocation(location);
        String url = getUrl(geocodeLocation);
        ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
        System.out.println(geocodeLocation.getDisplayName());
        return new WeatherResponse
                .Builder(geocodeLocation.getDisplayName())
                .withAlerts(response.getBody().getAlerts())
                .withDaily(response.getBody().getDaily())
                .withHourly(response.getBody().getHourly())
                .withCurrent(response.getBody().getCurrent())
                .build();
    }

    private String getUrl(GeocodeLocation geocodeLocation) {
        return OPEN_API_URL + "?lat=" + geocodeLocation.getLat() + "&lon=" + geocodeLocation.getLon() + "&appid=" + key + "&units=metric";
    }

    private Weather getLocationCoordinates(String location) {
        RestTemplate restTemplate = new RestTemplate();
        String url = LOCATION_URL + location + "&appId=" + key;
        try {
            System.out.println(geocodeService.getGeocodeLocation(location));
            return restTemplate.getForEntity(url, Weather.class).getBody();
        } catch (HttpClientErrorException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }


}
