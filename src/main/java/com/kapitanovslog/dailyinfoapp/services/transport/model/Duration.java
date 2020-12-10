package com.kapitanovslog.dailyinfoapp.services.transport.model;

public class Duration {
    private Long from;
    private Long until;
    private String text;

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getUntil() {
        return until;
    }

    public void setUntil(Long until) {
        this.until = until;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Duration{" +
                "from='" + from + '\'' +
                ", until='" + until + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
