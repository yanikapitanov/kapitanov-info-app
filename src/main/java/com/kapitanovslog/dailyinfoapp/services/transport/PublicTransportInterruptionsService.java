package com.kapitanovslog.dailyinfoapp.services.transport;

import com.kapitanovslog.dailyinfoapp.model.RequestMessage;
import com.kapitanovslog.dailyinfoapp.services.ServiceProvider;
import com.kapitanovslog.dailyinfoapp.services.transport.model.Interruption;
import com.kapitanovslog.dailyinfoapp.services.transport.model.Line;
import com.kapitanovslog.dailyinfoapp.services.transport.model.Transport;
import com.kapitanovslog.dailyinfoapp.services.transport.model.TransportResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PublicTransportInterruptionsService implements ServiceProvider {

    private static final String queryUrlName = "https://www.mvg.de/api/fahrinfo/location/queryWeb?q={name}";
    private static final String query_url_id = "https://www.mvg.de/api/fahrinfo/location/query?q={id}";
    private static final String departure_url = "https://www.mvg.de/api/fahrinfo/departure/{id}?footway={offset}";
    private static final String routing_url = "https://www.mvg.de/api/fahrinfo/routing/?";
    private static final String interruptions_url = "https://www.mvg.de/.rest/betriebsaenderungen/api/interruptions";
//    id_prefix = "de:09162:"

    public List<TransportResponse> getInterruptionsByLines(String line) {
        RestTemplate restTemplate = new RestTemplate();
        Transport data = restTemplate.getForObject(interruptions_url, Transport.class);

        List<Line> lineInterruptions = Optional.ofNullable(data.getLines()).orElseGet(ArrayList::new);

        if (null != line && !line.isEmpty()) {
            List<Line> userDisruptedLines = interruptedLines(lineInterruptions, line);
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


    private List<Line> interruptedLines(List<Line> lineInterruptions, String line) {
        return lineInterruptions
                .stream()
                .filter(matchLines(line))
                .collect(Collectors.toList());
    }

    private Predicate<Line> matchLines(String line) {
        if (line.matches("s[0-9]")) {
            return l -> l.getLine().equalsIgnoreCase(line) || l.getLine().equalsIgnoreCase("s-bahn");
        } else if (line.matches("u[0-9]")) {
            return l -> l.getLine().equalsIgnoreCase(line) || l.getLine().equalsIgnoreCase("s-bahn");
        }
        return l -> l.getLine().equalsIgnoreCase(line);
    }

    private List<TransportResponse> getInterruptionResult(List<Interruption> interruptions) {
        return interruptions
                .stream()
                .map(interruption -> {
                    TransportResponse response = new TransportResponse();
                    response.setTitle(interruption.getTitle());
                    response.setDuration(interruption.getDuration());
                    response.setLines(interruption.getAllLines());
                    response.setText(interruption.getText());
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String execute(RequestMessage requestMessage) {
        return "";
    }
}
