package com.kapitanovslog.dailyinfoapp.services.geolocation;

import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class GeocodeService {

    private final GeocodeClient geocodeClient;

    @Autowired
    GeocodeService(GeocodeClient geocodeClient) {
        this.geocodeClient = geocodeClient;
    }

    public Optional<GeocodeLocation> findGeoLocation(String location) {
        Objects.requireNonNull(location, "Location cannot be null");
        return geocodeClient.findGeocodeLocation(location).stream()
                .limit(1)
                .findFirst();
    }
}
