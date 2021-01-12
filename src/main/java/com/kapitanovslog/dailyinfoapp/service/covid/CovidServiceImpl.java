package com.kapitanovslog.dailyinfoapp.service.covid;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapitanovslog.dailyinfoapp.model.covid.Covid;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CovidServiceImpl implements CovidService {

    public static final String URL = "https://api.covid19api.com/dayone/country/";
    private final ObjectMapper mapper = new ObjectMapper();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    @Override
    public String getCovidInfoByCountry(String country) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            JsonNode response = restTemplate.getForObject(URL + country, JsonNode.class);
            List<Covid> covid = getCovidList(response);

            if (covid.isEmpty()) {
                return "No country found";
            }

            int len = covid.size();
            Covid current = covid.get(len - 1);
            Covid prev = covid.get(len - 2);
            return generateStringResponse(current, prev);

        } catch (RestClientException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private String generateStringResponse(Covid current, Covid prev) {
        CovidUtil util = new CovidUtil(current, prev);
        return "Country: " + current.getCountry() + "\n\n" +
                "Date: " + util.getDate().format(formatter) + "\n" +
                calculateIncrease("Confirmed: ", current.getConfirmed(), util.getConfirmedIncrease()) +
                calculateIncrease("Deaths: ", current.getDeaths(), util.getDeathsIncrease()) +
                calculateIncrease("Recovered: ", current.getRecovered(), util.getRecoveredIncrease()) +
                calculateIncrease("Active: ", current.getActive(), util.getActiveIncrease());
    }

    private String calculateIncrease(String label,long confirmed, long increase) {
        return label + resultOutputTemplating(confirmed,getSymbol(increase), increase);
    }

    private String resultOutputTemplating(double num1, String symbol, double num2) {
        return String.format("%.2f %s %.2f%n", num1, symbol, num2);
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
