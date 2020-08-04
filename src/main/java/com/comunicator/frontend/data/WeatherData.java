package com.comunicator.frontend.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lat",
        "lon",
        "timezone",
        "timezone_offset",
        "current"
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherData {

    @JsonProperty("lat")
    public Double lat;
    @JsonProperty("lon")
    public Double lon;
    @JsonProperty("timezone")
    public String timezone;
    @JsonProperty("timezone_offset")
    public Integer timezoneOffset;
    @JsonProperty("current")
    public Current current;
}
