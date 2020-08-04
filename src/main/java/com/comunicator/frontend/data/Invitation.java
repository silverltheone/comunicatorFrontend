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
        "senderId",
        "receiverId",
        "sendDate",
        "wasRead"
})
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Invitation {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("senderId")
    public Long senderId;
    @JsonProperty("receiverId")
    public Long receiverId;
    @JsonProperty("sendDate")
    public String sendDate;
    @JsonProperty("wasRead")
    public Boolean wasRead;
}
