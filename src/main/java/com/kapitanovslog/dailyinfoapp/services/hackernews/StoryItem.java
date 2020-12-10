package com.kapitanovslog.dailyinfoapp.services.hackernews;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
class StoryItem {

    private final String title;
    private final String url;
}
