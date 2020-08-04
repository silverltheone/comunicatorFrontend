package com.comunicator.frontend.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "dt",
        "sunrise",
        "sunset",
        "temp",
        "feels_like",
        "pressure",
        "humidity",
        "dew_point",
        "uvi",
        "clouds",
        "visibility",
        "wind_speed",
        "wind_deg",
        "weather"
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Current {

    @JsonProperty("dt")
    public Integer dt;
    @JsonProperty("sunrise")
    public Integer sunrise;
    @JsonProperty("sunset")
    public Integer sunset;
    @JsonProperty("temp")
    public Double temp;
    @JsonProperty("feels_like")
    public Double feelsLike;
    @JsonProperty("pressure")
    public Integer pressure;
    @JsonProperty("humidity")
    public Integer humidity;
    @JsonProperty("dew_point")
    public Double dewPoint;
    @JsonProperty("uvi")
    public Double uvi;
    @JsonProperty("clouds")
    public Integer clouds;
    @JsonProperty("visibility")
    public Integer visibility;
    @JsonProperty("wind_speed")
    public Double windSpeed;
    @JsonProperty("wind_deg")
    public Integer windDeg;
    @JsonProperty("weather")
    public List<Weather> weather;
}
