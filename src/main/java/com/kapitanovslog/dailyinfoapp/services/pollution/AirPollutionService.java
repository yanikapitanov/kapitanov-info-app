package com.kapitanovslog.dailyinfoapp.services.pollution;

import com.kapitanovslog.dailyinfoapp.model.RequestMessage;
import com.kapitanovslog.dailyinfoapp.services.ServiceProvider;
import com.kapitanovslog.dailyinfoapp.services.geolocation.GeocodeService;
import com.kapitanovslog.dailyinfoapp.services.geolocation.model.GeocodeLocation;
import com.kapitanovslog.dailyinfoapp.services.pollution.model.AirPollutionResource;
import com.kapitanovslog.dailyinfoapp.services.pollution.model.AirPollutionResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Objects;

@Log4j2
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

    private String findAirPollutionDetails(String location) {
        Objects.requireNonNull(location, "Geo location cannot be null");

        GeocodeLocation geoLocation = geocodeService.findGeoLocation(location);
        AirPollutionResource airPollutions = airPollutionClient.fetchAirPollutionDetails(geoLocation);
        log.info("Air pollution: {}", airPollutions);
        AirPollutionResponse pollution = airPollutionMappingService.fetchPollutionDetails(airPollutions, geoLocation.displayName());

        return pollution.getLocation() + "\n\n" +
                "Air Quality PM25: " + pollution.getPm25Quality() + "\n" +
                "Air Quality PM10: " + pollution.getPm10Quality() + "\n" +
                "Fine particles matter: " + DF.format(pollution.getPm25value()) + "\n" +
                "Coarse particulate matter: " + DF.format(pollution.getPm10Value()) + "\n";
    }

    @Override
    public String execute(RequestMessage requestMessage) {
        return findAirPollutionDetails(requestMessage.message());
    }
}
