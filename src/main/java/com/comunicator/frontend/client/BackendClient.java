package com.comunicator.frontend.client;

import com.comunicator.frontend.data.*;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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

    public User getOne(Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/"+id).build().encode().toUri();
        try {
            User response = restTemplate.getForObject(url,User.class);
            return response;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new User();
        }
    }

    public LocData getLocData(String ip) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/localisation/"+ip).build().encode().toUri();
            LocData response = restTemplate.getForObject(url,LocData.class);
            return response;
    }

    public WeatherData getWeatherData(Double lat, Double lon) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/weather/"+lat+"/"+lon).build().encode().toUri();
        WeatherData response = restTemplate.getForObject(url,WeatherData.class);
        return response;
    }

    public User getByEmail(String email) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user/getByEmail/" + email).build().encode().toUri();
        try {
            User response = restTemplate.getForObject(url, User.class);
            return response;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new User();
        }
    }

    public User createUser(UserToCreate userToCreate) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user")
                .build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(userToCreate);
        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);
        try {
            User userResponse = restTemplate.postForObject(url, request, User.class);
            return userResponse;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new User();
        }
    }

    public Message sendMessage(MessageToCreate messageToCreate) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/message")
                .build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(messageToCreate);
        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);
        try {
            Message messageResponse = restTemplate.postForObject(url, request, Message.class);
            return messageResponse;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new Message();
        }
    }

    public Invitation createInvitation(InvitationToCreate invitationToCreate) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/invitation")
                .build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(invitationToCreate);
        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);
        try {
            Invitation invitationResponse = restTemplate.postForObject(url, request, Invitation.class);
            return invitationResponse;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new Invitation();
        }
    }

    public void updateMessage(Message message) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/message")
                .build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(message);
        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);
        try {
             restTemplate.exchange(url, HttpMethod.PUT, request, void.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void updateInvitation(Invitation invitation) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/invitation")
                .build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(invitation);
        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, request, void.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void updateUser(User user) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/user")
                .build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(user);
        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);
        try {
            restTemplate.exchange(url, HttpMethod.PUT, request, void.class);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public InfoLog createInfoLog(InfoLogToCreate infoLogToCreate) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/infoLogs")
                .build().encode().toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(infoLogToCreate);
        HttpEntity<String> request = new HttpEntity<>(jsonContent, headers);
        try {
            InfoLog infoLogResponse = restTemplate.postForObject(url, request, InfoLog.class);
            return infoLogResponse;
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage());
            return new InfoLog();
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

    public List<Message> getEmailBySenderOrReceiverId(Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/message/getEmailBySenderOrReceiverId/"+id).build().encode().toUri();

        try {
            Message[] boardsResponse = restTemplate.getForObject(url, Message[].class);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new Message[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public List<Invitation> getInvitationBySenderOrReceiverId(Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/invitation/getInvitationBySenderOrReceiverId/"+id).build().encode().toUri();

        try {
            Invitation[] boardsResponse = restTemplate.getForObject(url, Invitation[].class);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new Invitation[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    public void deleteInvitation(Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/invitation/"+id).build().encode().toUri();
        try {
            restTemplate.delete(url);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void deleteMessage(Long id) {
        URI url = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/v1/message/"+id).build().encode().toUri();
        try {
            restTemplate.delete(url);
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
