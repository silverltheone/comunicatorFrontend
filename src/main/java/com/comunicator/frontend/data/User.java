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
        "id",
        "firstName",
        "lastName",
        "email",
        "password",
        "friendsId",
        "friendsOfId",
        "sentMessagesId",
        "receivedMessagesId"
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
    @JsonProperty("friendsId")
    public List<Long> friendsId;
    @JsonProperty("friendsOfId")
    public List<Long> friendsOfId;
    @JsonProperty("sentMessagesId")
    public List<Long> sentMessagesId;
    @JsonProperty("receivedMessagesId")
    public List<Long> receivedMessagesId;

    public String firstPlusLastName() {
        return this.getFirstName()+" " +this.getLastName();
    }
}
