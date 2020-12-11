package com.kapitanovslog.dailyinfoapp.controller;

import com.kapitanovslog.dailyinfoapp.model.transport.TransportResponse;
import com.kapitanovslog.dailyinfoapp.service.transport.PublicTransportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transport")
public class TransportController {

    private final PublicTransportService publicTransportService;

    public TransportController(PublicTransportService publicTransportService) {
        this.publicTransportService = publicTransportService;
    }

    @GetMapping("/interruptions")
    public List<TransportResponse> getInterruptions(@RequestParam(required = false) String... transportLines) {
        return publicTransportService.getInterruptionsByLines(transportLines);
    }
}
