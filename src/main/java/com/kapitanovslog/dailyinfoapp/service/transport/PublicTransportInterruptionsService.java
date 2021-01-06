package com.kapitanovslog.dailyinfoapp.service.transport;

import com.kapitanovslog.dailyinfoapp.model.transport.TransportResponse;

import java.util.List;

public interface PublicTransportInterruptionsService {
    List<TransportResponse> getInterruptionsByLines(String line);
}
