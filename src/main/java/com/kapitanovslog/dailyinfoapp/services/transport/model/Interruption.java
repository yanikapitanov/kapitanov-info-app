package com.kapitanovslog.dailyinfoapp.services.transport.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Interruption {
    private Long id;
    private String title;
    private Map<String, List<Line>> lines;
    private Duration duration;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, List<Line>> getLines() {
        return lines;
    }

    public List<Line> getAllLines() {
        return lines.get("line");
    }

    public void setLines(Map<String, List<Line>> lines) {
        this.lines = lines;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Interruption{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", lines=" + lines +
                ", duration=" + duration +
                ", text='" + text + '\'' +
                '}';
    }
}
