package com.kapitanovslog.dailyinfoapp.service.covid;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapitanovslog.dailyinfoapp.model.covid.Covid;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CovidServiceImpl implements CovidService {

    public static final String URL = "https://api.covid19api.com/dayone/country/";
    private final ObjectMapper mapper = new ObjectMapper();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat df = new DecimalFormat("###, ###, ###");



    @Override
    public String getCovidInfoByCountry(String country) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            JsonNode response = restTemplate.getForObject(URL + country, JsonNode.class);
            List<Covid> covid = getCovidList(response);
            int len = covid.size();

            if (covid.isEmpty()) {
                return "No country found";
            }
            Covid current = covid.get(len - 1);
            Covid prev = covid.get(len - 2);
            return generateStringResponse(current, prev);

        } catch (RestClientException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private String generateStringResponse(Covid current, Covid prev) {
        StringBuilder sb = new StringBuilder();
        sb.append("Covid stats:").append("\n\n");

        long activeIncrease = current.getActive() - prev.getActive();
        long deathsIncrease = current.getDeaths() - prev.getDeaths();
        long recoveredIncrease = current.getRecovered() - prev.getRecovered();
        long confirmedIncrease = current.getConfirmed() - prev.getConfirmed();

        String date = current.getDate().split("T")[0];

        sb.append("Country: ").append(current.getCountry()).append("\n\n");

        sb.append("Date: ").append(LocalDate.parse(date).format(formatter)).append("\n");

        sb.append("Confirmed: ")
                .append(df.format(current.getConfirmed()))
                .append(" ").append(getSymbol(confirmedIncrease))
                .append(" ")
                .append(df.format(confirmedIncrease))
                .append("\n");
        sb.append("Deaths: ")
                .append(df.format(current.getDeaths()))
                .append(" ")
                .append(getSymbol(deathsIncrease))
                .append(" ")
                .append(df.format(deathsIncrease))
                .append("\n");
        sb.append("Recovered: ")
                .append(df.format(current.getRecovered()))
                .append(" ").append(getSymbol(recoveredIncrease))
                .append(" ")
                .append(df.format(recoveredIncrease))
                .append("\n");
        sb.append("Active: ")
                .append(df.format(current.getActive()))
                .append(" ")
                .append(getSymbol(activeIncrease))
                .append(" ")
                .append(df.format(activeIncrease))
                .append("\n");

        return sb.toString();
    }

    private String getSymbol(long num) {
        if (num > 0) {
            return EmojiParser.parseToUnicode(":arrow_up:");
        } else if (num < 0) {
            return EmojiParser.parseToUnicode(":arrow_down:");
        } else {
            return EmojiParser.parseToUnicode(":arrow_right:");
        }
    }

    private List<Covid> getCovidList(JsonNode covidCases) {
        return mapper.convertValue(
                covidCases,
                new TypeReference<List<Covid>>() {
                }
        );
    }

}
