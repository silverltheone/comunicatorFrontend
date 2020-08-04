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
        "id",
        "userId",
        "date",
        "type"
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InfoLog {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("userId")
    public Long userId;
    @JsonProperty("date")
    public String date;
    @JsonProperty("type")
    public String type;
}
