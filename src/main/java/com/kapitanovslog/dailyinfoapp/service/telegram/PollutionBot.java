package com.kapitanovslog.dailyinfoapp.service.telegram;

import com.kapitanovslog.dailyinfoapp.model.pollution.ListItem;
import com.kapitanovslog.dailyinfoapp.model.pollution.Pollution;
import com.kapitanovslog.dailyinfoapp.service.pollution.PollutionService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class PollutionBot {


    private final PollutionService pollutionService;

    public PollutionBot(PollutionService pollutionService) {
        this.pollutionService = pollutionService;
    }

    public String getPollutionInfo(String location) {
        StringBuilder sb = new StringBuilder();
        Pollution pollution = pollutionService.getPollutionData(location);
        List<ListItem> pollutionItems = pollution.getList();
        sb.append(pollution.getLocation()).append("\n\n");
        pollutionItems.forEach(p -> {
            int aqi = p.getMain().getAqi();
            sb.append("Air Quality: ").append(setAirQuality(aqi)).append("\n");
            sb.append("Fine particles matter: ").append(p.getComponents().getPm25()).append("\n");
            sb.append("Coarse particulate matter: ").append(p.getComponents().getPm10()).append("\n");
        });
        return sb.toString();
    }

    private String setAirQuality(int aqi) {
        switch (aqi) {
            case 1:
                return "Good";
            case 2:
                return "Fair";
            case 3:
                return "Moderate";
            case 4:
                return "Poor";
            case 5:
                return "Very poor";
            default:
                return "No data";
        }
    }
}
