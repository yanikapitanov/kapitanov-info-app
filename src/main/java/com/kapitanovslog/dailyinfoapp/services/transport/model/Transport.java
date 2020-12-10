package com.kapitanovslog.dailyinfoapp.services.transport.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class Transport {

    @JsonProperty("affectedLines")
    private Map<String, List<Line>> affectedLines = new HashMap<>();
    @JsonProperty("interruption")
    private List<Interruption> interruptions = new ArrayList<>();

    public Map<String, List<Line>> getAffectedLines() {
        return affectedLines;
    }

    public List<Line> getLines() {
        return Optional.ofNullable(affectedLines.get("line")).orElse(new ArrayList<>());
    }

    public void setAffectedLines(Map<String, List<Line>> affectedLines) {
        this.affectedLines = affectedLines;
    }

    public List<Interruption> getInterruptions() {
        return interruptions;
    }

    public void setInterruptions(List<Interruption> interruptions) {
        this.interruptions = interruptions;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "affectedLines=" + affectedLines +
                ", interruptions=" + interruptions +
                '}';
    }
}
