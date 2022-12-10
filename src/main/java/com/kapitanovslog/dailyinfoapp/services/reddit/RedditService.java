package com.kapitanovslog.dailyinfoapp.services.reddit;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.image.BufferedImage;
import java.util.List;

@Log4j2
@Service
public class RedditService {

    public static final String URL = "https://www.reddit.com/r/memes/hot.json?count=10";

    private final WebClient webClient;

    @Autowired
    RedditService(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<BufferedImage> getTop10() {
        JsonNode data = getData();
        log.info(data.toPrettyString());
        List<String> lastTenPostsNonGifsUrls = parseRedditData(data);
        return null;
    }

    private JsonNode getData() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<JsonNode> node = restTemplate.getForEntity(URL, JsonNode.class);
        } catch (HttpClientErrorException e){
            log.error(e.getMessage());
        }
        return null;
    }

    private List<String> parseRedditData(JsonNode node) {
        return null;
    }
}
