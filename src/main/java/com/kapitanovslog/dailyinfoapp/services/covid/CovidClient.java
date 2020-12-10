package com.kapitanovslog.dailyinfoapp.services.covid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Component
class CovidClient {

    public static final String URL = "https://api.covid19api.com/dayone/country/";
    private final WebClient webClient;

    @Autowired
    CovidClient(WebClient webClient) {
        this.webClient = webClient;
    }

    List<Covid> getCovidInfoByCountry(String country) {
       return webClient.get()
                .uri(URI.create(URL + country))
                .retrieve()
                .bodyToMono(Covid[].class)
                .map(Arrays::asList)
                .blockOptional()
                .orElseGet(List::of);

    }
}
