package com.kapitanovslog.dailyinfoapp.services.covid;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CovidService {


    private final CovidClient webClient;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    CovidService(CovidClient webClient) {
        this.webClient = webClient;
    }

    public String findCovidInfoByCountry(String country) {
        List<Covid> covid = webClient.getCovidInfoByCountry(country);

        if (covid.isEmpty()) {
            return "No country found";
        }

        int len = covid.size();
        Covid current = covid.get(len - 1);
        Covid prev = covid.get(len - 2);
        return generateStringResponse(current, prev);
    }

    private String generateStringResponse(Covid current, Covid prev) {
        CovidDetails details = CovidDetails.of(current, prev);
        return "Country: " + current.getCountry() + "\n\n" +
                "Date: " + details.getDate().format(DATE_TIME_FORMATTER) + "\n" +
                calculateIncrease("Confirmed: ", current.getConfirmed(), details.getConfirmedIncrease()) +
                calculateIncrease("Deaths: ", current.getDeaths(), details.getDeathsIncrease()) +
                calculateIncrease("Recovered: ", current.getRecovered(), details.getRecoveredIncrease()) +
                calculateIncrease("Active: ", current.getActive(), details.getActiveIncrease());
    }

    private String calculateIncrease(String label, long confirmed, long increase) {
        return label + resultOutputTemplating(confirmed, mapIcon(increase), increase);
    }

    private String resultOutputTemplating(double num1, String symbol, double num2) {
        return String.format("%.2f %s %.2f%n", num1, symbol, num2);
    }

    private String mapIcon(long num) {
        if (num > 0) {
            return EmojiParser.parseToUnicode(":arrow_up:");
        } else if (num < 0) {
            return EmojiParser.parseToUnicode(":arrow_down:");
        } else {
            return EmojiParser.parseToUnicode(":arrow_right:");
        }
    }
}
