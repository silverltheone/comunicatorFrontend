package com.comunicator.frontend.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<User> friends;
    private List<User> friendOf;
    private List<Message> sentMessages;
    private List<Message> receivedMessages;
}
