package com.kapitanovslog.dailyinfoapp.services.transport;

import com.kapitanovslog.dailyinfoapp.services.transport.model.Line;
import com.kapitanovslog.dailyinfoapp.services.transport.model.TransportResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TelegramBotTransport {

    private final PublicTransportInterruptionsService service;


    public TelegramBotTransport(PublicTransportInterruptionsService service) {
        this.service = service;
    }

    public String provideInterruptions(String line) {
        String disruptions = service.getInterruptionsByLines(line)
                .stream()
                .map(this::telegramResponseFormatter)
                .collect(Collectors.joining("\n------------------------------------\n"));
        return disruptions.isEmpty() ? "No disruptions found" : disruptions;

    }

    public String telegramResponseFormatter(TransportResponse interruption) {
        StringBuilder output = new StringBuilder();
        output.append(String.format("%s%n%n", interruption.getTitle()));
        output.append(String.format("%s%n%n", interruption.getText()));
        String lines = getLinesAffected(interruption.getLines());
        output.append(lines);
        return output.toString();
    }

    public String getLinesAffected(List<Line> lines) {
        return String.format("Lines affected %s%n", lines.stream().map(Line::getLine).collect(Collectors.joining(", ")));
    }
}
