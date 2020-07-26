package com.comunicator.frontend.service;

import com.comunicator.frontend.client.BackendClient;
import com.comunicator.frontend.data.CreatedUser;
import com.comunicator.frontend.data.Message;
import com.comunicator.frontend.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackendService {

    @Autowired
    private BackendClient client;


    public List<User> getUsers() {
        return client.getUsers();
    }

    public List<Message> getMessages() {
        return client.getMessages();
    }

    public CreatedUser createUser(User user) {
        return client.createUser(user);
    }
}
