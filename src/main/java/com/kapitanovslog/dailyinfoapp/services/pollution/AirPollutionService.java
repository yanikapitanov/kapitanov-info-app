package com.kapitanovslog.dailyinfoapp.services.pollution;

import com.kapitanovslog.dailyinfoapp.model.RequestMessage;
import com.kapitanovslog.dailyinfoapp.services.ServiceProvider;
import com.kapitanovslog.dailyinfoapp.services.geolocation.GeocodeService;
import com.kapitanovslog.dailyinfoapp.services.pollution.model.AirPollution;
import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.services.pollution.model.AirPollutionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

@Service("/pollution")
public class AirPollutionService implements ServiceProvider {

    public static final DecimalFormat DF = new DecimalFormat("#.00");

    private final AirPollutionClient airPollutionClient;
    private final  AirPollutionMappingService airPollutionMappingService;
    private final GeocodeService geocodeService;

    @Autowired
    AirPollutionService(AirPollutionClient webClient,
                        AirPollutionMappingService airPollutionMappingService,
                        GeocodeService geocodeService) {
        this.airPollutionClient = webClient;
        this.airPollutionMappingService = airPollutionMappingService;
        this.geocodeService = geocodeService;
    }

    public String findAirPollutionDetails(String location) {
        Objects.requireNonNull(location, "Geo location cannot be null");

        GeocodeLocation geoLocation = geocodeService.findGeoLocation(location);
        int area = mapRegionArea(geoLocation.type());
        List<AirPollution> airPollutions = airPollutionClient.fetchAirPollutionDetails(geoLocation, area);
        AirPollutionResponse pollution = airPollutionMappingService.fetchPollutionDetails(airPollutions, geoLocation.displayName());

        return pollution.getLocation() + "\n\n" +
                "Air Quality PM25: " + pollution.getPm25Quality() + "\n" +
                "Air Quality PM10: " + pollution.getPm10Quality() + "\n" +
                "Fine particles matter: " + DF.format(pollution.getPm25value()) + "\n" +
                "Coarse particulate matter: " + DF.format(pollution.getPm10Value()) + "\n";
    }

    private int mapRegionArea(String location) {
        if (location.equalsIgnoreCase("suburb")) {
            return 1;
        }
        if (location.equalsIgnoreCase("town")) {
            return 15;
        }
        if (location.equalsIgnoreCase("administrative")) {
            return 300;
        }
        return 1;
    }

    @Override
    public String execute(RequestMessage requestMessage) {
        return "";
    }
}
