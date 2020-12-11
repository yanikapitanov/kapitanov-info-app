package com.kapitanovslog.dailyinfoapp.service.transport;

import com.kapitanovslog.dailyinfoapp.model.transport.TransportResponse;

import java.util.List;

public interface PublicTransportService {
    List<TransportResponse> getInterruptionsByLines(String... transportLines);
}
