package com.comunicator.frontend.service;

import com.comunicator.frontend.client.BackendClient;
import com.comunicator.frontend.data.*;
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

    public User getOne(Long id) {
        return client.getOne(id);
    }

    public LocData getLocData(String ip) {
        return client.getLocData(ip);
    }

    public WeatherData getWeatherData(Double lat, Double lon) {
        return client.getWeatherData(lat, lon);
    }

    public User getByEmail(String email) {
        return client.getByEmail(email);
    }

    public List<Message> getMessages() {
        return client.getMessages();
    }

    public User createUser(UserToCreate userToCreate) {
       return client.createUser(userToCreate);
    }

    public Message sendMessage(MessageToCreate messageToCreate) {
        return client.sendMessage(messageToCreate);
    }

    public InfoLog createInfoLog(InfoLogToCreate infoLogToCreate) {
        return client.createInfoLog(infoLogToCreate);
    }

    public Invitation createInvitation(InvitationToCreate invitationToCreate) {
        return client.createInvitation(invitationToCreate);
    }

    public List<Message> getEmailBySenderOrReceiverId(Long id) {
        return client.getEmailBySenderOrReceiverId(id);
    }

    public List<Invitation> getInvitationBySenderOrReceiverId(Long id) {
        return client.getInvitationBySenderOrReceiverId(id);
    }

    public void updateMessage(Message message) {
        client.updateMessage(message);
    }

    public void updateInvitation(Invitation invitation) {
        client.updateInvitation(invitation);
    }

    public void updateUser(User user) {
        client.updateUser(user);
    }

    public void deleteInvitation(Long id) {
        client.deleteInvitation(id);
    }

    public void deleteMessage(Long id) {
        client.deleteMessage(id);
    }
}
