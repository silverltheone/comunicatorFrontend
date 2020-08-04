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
        "status",
        "country",
        "countryCode",
        "region",
        "regionName",
        "city",
        "zip",
        "lat",
        "lon",
        "timezone",
        "isp",
        "org",
        "as",
        "query"
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocData {

    @JsonProperty("status")
    public String status;
    @JsonProperty("country")
    public String country;
    @JsonProperty("countryCode")
    public String countryCode;
    @JsonProperty("region")
    public String region;
    @JsonProperty("regionName")
    public String regionName;
    @JsonProperty("city")
    public String city;
    @JsonProperty("zip")
    public String zip;
    @JsonProperty("lat")
    public Double lat;
    @JsonProperty("lon")
    public Double lon;
    @JsonProperty("timezone")
    public String timezone;
    @JsonProperty("isp")
    public String isp;
    @JsonProperty("org")
    public String org;
    @JsonProperty("as")
    public String as;
    @JsonProperty("query")
    public String query;
}
