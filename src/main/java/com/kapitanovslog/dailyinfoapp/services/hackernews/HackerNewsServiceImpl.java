package com.kapitanovslog.dailyinfoapp.services.hackernews;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class HackerNewsServiceImpl implements HackerNewsService {

    public static final String HACKERNEWS_TOP_STORIES = "https://hacker-news.firebaseio.com/v0/topstories.json";
    public static final String hackerNewsItemsUrl = "https://hacker-news.firebaseio.com/v0/item/%s.json";

    @Override
    public String getHackerNewsTopStoriesIds() throws InterruptedException {
        return getStories().stream().map(story -> String.format("%s %n %s %n%n",story.getTitle(), story.getUrl())).collect(Collectors.joining());
    }

    private List<Long> getTopNewsIds() {
        RestTemplate template = new RestTemplate();
        ResponseEntity<Long[]> storyIds = template.getForEntity(HACKERNEWS_TOP_STORIES, Long[].class);
        if (storyIds.hasBody() && storyIds.getBody() != null) {
            return Arrays.stream(storyIds.getBody())
                    .limit(10)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private List<StoryItem> getStories() throws InterruptedException {
        RestTemplate template = new RestTemplate();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Long> ids = getTopNewsIds();
        Set<Callable<StoryItem>> callables = new HashSet<>();
        ids.forEach(id -> callables.add(() -> template.getForObject(String.format(hackerNewsItemsUrl, id), StoryItem.class)));
        List<Future<StoryItem>> stories = executorService.invokeAll(callables);

        return stories.stream().map(futureStoryItemFunction).collect(Collectors.toList());
    }

    private final Function<Future<StoryItem>, StoryItem> futureStoryItemFunction = future -> {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return new StoryItem("", "");
    };

}
