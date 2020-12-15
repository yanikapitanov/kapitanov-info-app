package com.kapitanovslog.dailyinfoapp.service.weather;

import com.kapitanovslog.dailyinfoapp.model.weather.WeatherResponse;
import com.kapitanovslog.dailyinfoapp.model.weather.location.Coord;
import com.kapitanovslog.dailyinfoapp.model.weather.location.Weather;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static final String LOCATION_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final String OPEN_API_URL = "https://api.openweathermap.org/data/2.5/onecall";

    @Value("${weather-app-key}")
    private String key;

    @Override
    public WeatherResponse getWeatherByLocation(String location) {
        RestTemplate restTemplate = new RestTemplate();
        Weather coord = getLocationCoordinates(location);
        String url = getUrl(coord.getCoord());
        ResponseEntity<Response> response = restTemplate.getForEntity(url, Response.class);
        return new WeatherResponse
                .Builder(location)
                .withCountry(coord.getSys().getCountry())
                .withAlerts(response.getBody().getAlerts())
                .withDaily(response.getBody().getDaily())
                .withHourly(response.getBody().getHourly())
                .withCurrent(response.getBody().getCurrent())
                .build();
    }

    private String getUrl(Coord coord) {
        return OPEN_API_URL + "?lat="+ coord.getLat() + "&lon=" + coord.getLon() + "&appid=" + key + "&units=metric";
    }

    private Weather getLocationCoordinates(String location) {
        RestTemplate restTemplate = new RestTemplate();
        String url = LOCATION_URL + location + "&appId=" + key;
        try {
            return restTemplate.getForEntity(url, Weather.class).getBody();
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
