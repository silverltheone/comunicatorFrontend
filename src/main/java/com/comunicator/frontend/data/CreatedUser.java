package com.comunicator.frontend.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedUser {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String email;

    @JsonProperty("shortUrl")
    private String shortUrl;
}
