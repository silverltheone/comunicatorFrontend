package com.comunicator.frontend.client;

import com.comunicator.frontend.data.CreatedUser;
import com.comunicator.frontend.data.LoggedUser;
import com.comunicator.frontend.data.Message;
import com.comunicator.frontend.data.User;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class BackendClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BackendClient.class);

    @Autowired
    private RestTemplate restTemplate;

    public List<User> getUsers() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user").build().encode().toUri();

        try {
            User[] boardsResponse = restTemplate.getForObject(url, User[].class);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new User[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public LoggedUser getByEmailAndPassword(String email, String password) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/getByEmailAndPassword/" + email + "/" + password).build().encode().toUri();

        try {
            LoggedUser response = restTemplate.getForObject(url, LoggedUser.class);
            return response;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new LoggedUser();
        }
    }

    public List<Message> getMessages() {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/message").build().encode().toUri();

        try {
            Message[] boardsResponse = restTemplate.getForObject(url, Message[].class);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new Message[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public CreatedUser createUser(User user) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user")
                .build().encode().toUri();
        Gson gson = new Gson();
        String jsonContent = gson.toJson(user);
        return restTemplate.postForObject(url, jsonContent, CreatedUser.class);
    }
}
