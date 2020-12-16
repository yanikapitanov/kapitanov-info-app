package com.kapitanovslog.dailyinfoapp.service.geocode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapitanovslog.dailyinfoapp.model.geocode.GeocodeLocation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GeocodeServiceImpl implements GeocodeService {

    // https://nominatim.openstreetmap.org/search?q=donnersbergerbrucke&format=json&polygon=1&addressdetails=1&type=suburb

    public static final String URL_PREFIX = "https://nominatim.openstreetmap.org/search?q=";
    public static final String URL_SUFFIX = "&format=json&polygon=1&addressdetails=1&type=suburb";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public GeocodeLocation getGeocodeLocation(String location) {
        GeocodeLocation geocodeLocation = new GeocodeLocation();
        RestTemplate template = new RestTemplate();
        String url = URL_PREFIX + location + URL_SUFFIX;
        String locations = template.getForObject(url, String.class);
        try {
            List<Map<String, String>> geolocations = objectMapper.readValue(locations, ArrayList.class);
            Map<String, String> l =  geolocations.get(0);
            geocodeLocation.setDisplayName(l.get("display_name"));
            geocodeLocation.setLon(l.get("lon"));
            geocodeLocation.setLat(l.get("lat"));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return geocodeLocation;
    }
}
