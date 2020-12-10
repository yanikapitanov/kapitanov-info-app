package com.kapitanovslog.dailyinfoapp.services.transport;

import com.kapitanovslog.dailyinfoapp.services.transport.model.TransportResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transport")
public class TransportController {

    private final PublicTransportInterruptionsService publicTransportInterruptionsService;

    public TransportController(PublicTransportInterruptionsService publicTransportInterruptionsService) {
        this.publicTransportInterruptionsService = publicTransportInterruptionsService;
    }

    @GetMapping("/interruptions")
    public List<TransportResponse> getInterruptions(@RequestParam(required = false) String transportLines) {
        return publicTransportInterruptionsService.getInterruptionsByLines(transportLines);
    }
}
