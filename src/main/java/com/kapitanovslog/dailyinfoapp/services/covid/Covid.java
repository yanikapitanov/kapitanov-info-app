package com.kapitanovslog.dailyinfoapp.services.covid;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Covid {
    @JsonProperty("Country")
    private String country;
    @JsonProperty("Province")
    private String province;
    @JsonProperty("CountryCode")
    private String countryCode;
    @JsonProperty("Confirmed")
    private Long confirmed;
    @JsonProperty("Deaths")
    private Long deaths;
    @JsonProperty("Recovered")
    private Long recovered;
    @JsonProperty("Active")
    private Long active;
    @JsonProperty("Date")
    private LocalDateTime date;

}
