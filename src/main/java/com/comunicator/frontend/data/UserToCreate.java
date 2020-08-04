package com.comunicator.frontend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserToCreate {

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public List<Long> friendsId = new ArrayList<>();
    public List<Long> friendsOfId = new ArrayList<>();
    public List<Long> sentMessagesId = new ArrayList<>();
    public List<Long> receivedMessagesId = new ArrayList<>();
}

