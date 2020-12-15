package com.kapitanovslog.dailyinfoapp.model.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.AlertsItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.Current;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.DailyItem;
import com.kapitanovslog.dailyinfoapp.model.weather.openapi.HourlyItem;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public static class Builder {
        private final String location;
        private Current current;
        private String country;
        private List<HourlyItem> hourly;
        private List<DailyItem> daily;
        private List<AlertsItem> alerts;

        public Builder(String location) {
            this.location = location;
        }

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withCurrent(Current current) {
            this.current = current;
            return this;
        }

        public Builder withHourly(List<HourlyItem> hourly) {
            this.hourly = hourly;
            return this;
        }

        public Builder withDaily(List<DailyItem> daily) {
            this.daily = daily;
            return this;
        }

        public Builder withAlerts(List<AlertsItem> alerts) {
            this.alerts = alerts;
            return this;
        }

        public WeatherResponse build() {
            WeatherResponse response = new WeatherResponse();
            response.location = this.location;
            response.country = this.country;
            response.daily = this.daily;
            response.hourly = this.hourly;
            response.alerts = this.alerts;
            response.current = this.current;
            return response;
        }

    }
    private Current current;
    private String location;
    private String country;
    private List<HourlyItem> hourly;
    private List<DailyItem> daily;
    private List<AlertsItem> alerts;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<HourlyItem> getHourly() {
        return hourly;
    }

    public void setHourly(List<HourlyItem> hourly) {
        this.hourly = hourly;
    }

    public List<DailyItem> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyItem> daily) {
        this.daily = daily;
    }

    public List<AlertsItem> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertsItem> alerts) {
        this.alerts = alerts;
    }
}
