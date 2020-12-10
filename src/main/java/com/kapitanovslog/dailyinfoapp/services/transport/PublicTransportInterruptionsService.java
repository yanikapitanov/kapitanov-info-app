package com.kapitanovslog.dailyinfoapp.services.transport;

import com.kapitanovslog.dailyinfoapp.services.transport.model.TransportResponse;

import java.util.List;

public interface PublicTransportInterruptionsService {
    List<TransportResponse> getInterruptionsByLines(String line);
}
