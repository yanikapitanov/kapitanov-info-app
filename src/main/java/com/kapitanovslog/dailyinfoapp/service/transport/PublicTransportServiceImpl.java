package com.kapitanovslog.dailyinfoapp.service.transport;

import com.kapitanovslog.dailyinfoapp.model.transport.Interruption;
import com.kapitanovslog.dailyinfoapp.model.transport.Line;
import com.kapitanovslog.dailyinfoapp.model.transport.Transport;
import com.kapitanovslog.dailyinfoapp.model.transport.TransportResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PublicTransportServiceImpl implements PublicTransportService {

    private static final String queryUrlName = "https://www.mvg.de/api/fahrinfo/location/queryWeb?q={name}";
    private static final String query_url_id = "https://www.mvg.de/api/fahrinfo/location/query?q={id}";
    private static final String departure_url = "https://www.mvg.de/api/fahrinfo/departure/{id}?footway={offset}";
    private static final String routing_url = "https://www.mvg.de/api/fahrinfo/routing/?";
    private static final String interruptions_url = "https://www.mvg.de/.rest/betriebsaenderungen/api/interruptions";
//    id_prefix = "de:09162:"

    @Override
    public List<TransportResponse> getInterruptionsByLines(String... userLines) {
        RestTemplate restTemplate = new RestTemplate();
        Transport data = restTemplate.getForObject(interruptions_url, Transport.class);

        List<Line> lineInterruptions = Optional.ofNullable(data.getLines()).orElse(new ArrayList<>());
        if (null != userLines && userLines.length > 0) {
            List<Line> userDisruptedLines = matchInterruptionsAndUserLines(lineInterruptions, userLines);
            List<Interruption> userMatchedInterruptions = getUserInterruptions(userDisruptedLines, data.getInterruptions());
            return getInterruptionResult(userMatchedInterruptions);
        }
        return getInterruptionResult(data.getInterruptions());
    }

    private List<Interruption> getUserInterruptions(List<Line> userDisruptedLines, List<Interruption> interruptions) {
        List<Interruption> matched = new ArrayList<>();
        userDisruptedLines.forEach(line -> matched.addAll(produceInterruptions(interruptions, line)));
        return matched;
    }

    private List<Interruption> produceInterruptions(List<Interruption> interruptions, Line line) {

        return interruptions.stream()
                .filter(interruption -> interruption.getAllLines() != null)
                .filter(interruption -> interruption
                        .getLines()
                        .get("line")
                        .contains(line))
                .collect(Collectors.toList()
                );
    }

    private List<Line> matchInterruptionsAndUserLines(List<Line> lineInterruptions, String... userLines) {
        List<Line> matched = new ArrayList<>();
        for (String line : userLines) {
            matched.addAll(
                    lineInterruptions
                            .stream()
                            .filter(l -> l.getLine().equalsIgnoreCase(line))
                            .collect(Collectors.toList()));
        }
        return matched;
    }

    private List<TransportResponse> getInterruptionResult(List<Interruption> interruptions) {
        List<TransportResponse> matchedInterruptions = new ArrayList<>();
        interruptions.forEach(interruption -> {
            TransportResponse response = new TransportResponse();
            response.setTitle(interruption.getTitle());
            response.setDuration(interruption.getDuration());
            response.setLines(interruption.getAllLines());
            response.setText(interruption.getText());
            matchedInterruptions.add(response);
        });
        return matchedInterruptions;
    }
}
