package com.kapitanovslog.dailyinfoapp.service.geocode;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapitanovslog.dailyinfoapp.model.geocode.GeocodeLocation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.function.Function;

@Service
public class GeocodeServiceImpl implements GeocodeService {

    // https://nominatim.openstreetmap.org/search?q=donnersbergerbrucke&format=json&polygon=1&addressdetails=1&type=suburb

    public static final String URL_PREFIX = "https://nominatim.openstreetmap.org/search?q=";
    public static final String URL_SUFFIX = "&format=json&polygon=1&addressdetails=1&type=suburb";

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public GeocodeLocation getGeocodeLocation(String location) {
        RestTemplate template = new RestTemplate();
        String url = URL_PREFIX + location + URL_SUFFIX;
        JsonNode locations = template.getForObject(url, JsonNode.class);
        return getGeoLocation(locations);
    }

    private GeocodeLocation getGeoLocation(JsonNode locations) {
        return mapper.convertValue(
                locations,
                new TypeReference<List<GeocodeLocation>>(){}
        ).get(0);
    }

    private final Function<JsonNode, GeocodeLocation> getGeocodeLocation = node -> mapper.convertValue(
            node,
            new TypeReference<List<GeocodeLocation>>(){}
    ).get(0);
}
