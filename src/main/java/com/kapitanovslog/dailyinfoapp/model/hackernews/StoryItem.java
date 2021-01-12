package com.kapitanovslog.dailyinfoapp.model.hackernews;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class StoryItem {

    private String title;
    private String url;
}
