package com.kapitanovslog.dailyinfoapp.services.geolocation;

import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GeocodeService {

    private final GeocodeClient geocodeClient;

    @Autowired
    GeocodeService(GeocodeClient geocodeClient) {
        this.geocodeClient = geocodeClient;
    }

    public GeocodeLocation findGeoLocation(String location) {
        Objects.requireNonNull(location, "Location cannot be null");
        return geocodeClient.findGeocodeLocation(location).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Could not find geo location"));
    }
}
