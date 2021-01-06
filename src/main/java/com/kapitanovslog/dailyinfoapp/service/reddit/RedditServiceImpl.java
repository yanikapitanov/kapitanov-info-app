package com.kapitanovslog.dailyinfoapp.service.reddit;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.awt.image.BufferedImage;
import java.util.List;

@Slf4j
@Service
public class RedditServiceImpl implements RedditService {

    public static final String URL = "https://www.reddit.com/r/memes/hot.json?count=10";

    @Override
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
