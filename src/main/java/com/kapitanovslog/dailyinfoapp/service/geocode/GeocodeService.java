package com.kapitanovslog.dailyinfoapp.service.geocode;

import com.kapitanovslog.dailyinfoapp.model.geocode.GeocodeLocation;

public interface GeocodeService {
    GeocodeLocation getGeocodeLocation(String location);
}
