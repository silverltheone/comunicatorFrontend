package com.comunicator.frontend.client;

import com.comunicator.frontend.data.CreatedUser;
import com.comunicator.frontend.data.Message;
import com.comunicator.frontend.data.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        try{
            ResponseEntity<CreatedUser> entity = restTemplate.postForEntity(
                    "http://localhost:8080/v1/user",
                    user,
                    CreatedUser.class);
            return entity.getBody();
        }catch (RestClientException e){
            throw new RuntimeException(e);
        }
    }
}
