package com.kapitanovslog.dailyinfoapp.services.covid;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
class CovidDetails {

    private final LocalDate date;
    private final String country;
    private final long activeIncrease;
    private final long deathsIncrease;
    private final long recoveredIncrease;
    private final long confirmedIncrease;

    private CovidDetails(Covid current, Covid prev) {
        this.activeIncrease = current.getActive() - prev.getActive();
        this.deathsIncrease = current.getDeaths() - prev.getDeaths();
        this.recoveredIncrease = current.getRecovered() - prev.getRecovered();
        this.confirmedIncrease = current.getConfirmed() - prev.getConfirmed();
        this.country = current.getCountry();
        this.date = current.getDate().toLocalDate();
    }

    static CovidDetails of(Covid current, Covid prev) {
        Objects.requireNonNull(current);
        Objects.requireNonNull(prev);
        return new CovidDetails(current, prev);
    }
}
