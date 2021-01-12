package com.kapitanovslog.dailyinfoapp.service.covid;

import com.kapitanovslog.dailyinfoapp.model.covid.Covid;

import java.time.LocalDate;

public class CovidUtil {

    private final LocalDate date;
    private final String country;
    private final long activeIncrease;
    private final long deathsIncrease;
    private final long recoveredIncrease;
    private final long confirmedIncrease;

    public CovidUtil(Covid current, Covid prev) {
        this.activeIncrease = current.getActive() - prev.getActive();
        this.deathsIncrease = current.getDeaths() - prev.getDeaths();
        this.recoveredIncrease = current.getRecovered() - prev.getRecovered();
        this.confirmedIncrease = current.getConfirmed() - prev.getConfirmed();
        this.country = current.getCountry();
        this.date = LocalDate.parse(current.getDate().split("T")[0]);
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCountry() {
        return country;
    }

    public long getActiveIncrease() {
        return activeIncrease;
    }

    public long getDeathsIncrease() {
        return deathsIncrease;
    }

    public long getRecoveredIncrease() {
        return recoveredIncrease;
    }

    public long getConfirmedIncrease() {
        return confirmedIncrease;
    }
}
