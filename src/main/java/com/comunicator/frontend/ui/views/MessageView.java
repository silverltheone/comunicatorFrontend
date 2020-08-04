package com.comunicator.frontend.ui.views;

import com.comunicator.frontend.data.*;
import com.comunicator.frontend.service.BackendService;
import com.comunicator.frontend.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;

@Route(value = "MessagesArchive", layout = MainLayout.class)
public class MessageView extends VerticalLayout {

    private Grid<Message> messageGrid = new Grid<>(Message.class);
    private Grid<Invitation> invitationGrid = new Grid<>(Invitation.class);
    private Text messages = new Text("Messages");
    private Text invitations = new Text("Invitations");

    private BackendService backendService;

    public MessageView(BackendService backendService) {
        this.backendService = backendService;
        setSizeFull();

        configureMessageGrid();
        configureInvitationGrid();
        updateMessageList();
        updateInvitationList();

        messageGrid.asSingleSelect().addValueChangeListener(evt -> updateMessage(evt.getValue()));

        messageGrid.addComponentColumn(item -> createDeleteButton(messageGrid, item))
                .setHeader("Delete");

        invitationGrid.addComponentColumn(item -> createAcceptButton(invitationGrid, item))
                .setHeader("Accept");
        invitationGrid.setSelectionMode(Grid.SelectionMode.NONE);

        invitationGrid.addComponentColumn(item -> createRejectButton(invitationGrid, item))
                .setHeader("Reject");
        invitationGrid.setSelectionMode(Grid.SelectionMode.NONE);


        add(messages, messageGrid, invitations, invitationGrid);
    }

    private Component createDeleteButton(Grid<Message> messageGrid, Message message) {
        @SuppressWarnings("unchecked")
        Button deleteButton = new Button("Delete", clickevent -> deleteMessage(message));
        return deleteButton;
    }

    private Component createAcceptButton(Grid<Invitation> invitationGrid, Invitation invitation) {
        @SuppressWarnings("unchecked")
        Button acceptButton = new Button("Accept", clickevent -> acceptInvitation(invitation));
        return acceptButton;
    }

    private Component createRejectButton(Grid<Invitation> invitationGrid, Invitation invitation) {
        @SuppressWarnings("unchecked")
        Button rejectButton = new Button("Reject", clickevent -> rejectInvitation(invitation));
        return rejectButton;
    }

    private void deleteMessage(Message message) {
        backendService.deleteMessage(message.getId());
        updateMessageList();
        InfoLogToCreate infoLogToCreate = new InfoLogToCreate();
        infoLogToCreate.setUserId(backendService.getByEmail(setEmailValue()).getId());
        infoLogToCreate.setDate(LocalDate.now().toString());
        infoLogToCreate.setType("DELETE_MESSAGE");
        backendService.createInfoLog(infoLogToCreate);
    }

    private void acceptInvitation(Invitation invitation) {
        if (!invitation.wasRead) {
            if (invitation.getSenderId() != backendService.getByEmail(setEmailValue()).getId()) {
                User updaterUser = backendService.getOne(invitation.getReceiverId());
                updaterUser.getFriendsId().add(invitation.getSenderId());
                updaterUser.getFriendsOfId().add(invitation.getSenderId());
                backendService.updateUser(updaterUser);
                backendService.sendMessage(new MessageToCreate(
                        invitation.getReceiverId(),
                        invitation.getSenderId(),
                        LocalDate.now().toString(),
                        "User " + backendService.getOne(invitation.getReceiverId()).firstPlusLastName() + " accept your invitation",
                        false));
                backendService.updateInvitation(new Invitation(invitation.getId(),
                        invitation.getSenderId(),
                        invitation.getReceiverId(),
                        invitation.getSendDate(),
                        true));
                updateInvitationList();
                InfoLogToCreate infoLogToCreate = new InfoLogToCreate();
                infoLogToCreate.setUserId(backendService.getByEmail(setEmailValue()).getId());
                infoLogToCreate.setDate(LocalDate.now().toString());
                infoLogToCreate.setType("ACCEPT_INVITATION");
                backendService.createInfoLog(infoLogToCreate);
            } else {
                Notification youreInvitation = new Notification("You cannot accept you're own invitation");
                youreInvitation.setPosition(Notification.Position.MIDDLE);
                youreInvitation.setDuration(5000);
                youreInvitation.open();
            }
        } else {
            Notification alreadyRead = new Notification("This invitations was already read");
            alreadyRead.setPosition(Notification.Position.MIDDLE);
            alreadyRead.open();
            alreadyRead.setDuration(5000);
        }
    }

    private void rejectInvitation(Invitation invitation) {
        if (!invitation.wasRead) {
            if (invitation.getSenderId() != backendService.getByEmail(setEmailValue()).getId()) {
                backendService.sendMessage(new MessageToCreate(
                        invitation.getReceiverId(),
                        invitation.getSenderId(),
                        LocalDate.now().toString(),
                        "User " + backendService.getOne(invitation.getReceiverId()).firstPlusLastName() + " reject your invitation",
                        false));
                backendService.updateInvitation(new Invitation(invitation.getId(),
                        invitation.getSenderId(),
                        invitation.getReceiverId(),
                        invitation.getSendDate(),
                        true));
                updateInvitationList();
                InfoLogToCreate infoLogToCreate = new InfoLogToCreate();
                infoLogToCreate.setUserId(backendService.getByEmail(setEmailValue()).getId());
                infoLogToCreate.setDate(LocalDate.now().toString());
                infoLogToCreate.setType("REJECT_INVITATION");
                backendService.createInfoLog(infoLogToCreate);
            } else {
                Notification youreInvitation = new Notification("You cannot reject you're own invitation");
                youreInvitation.setPosition(Notification.Position.MIDDLE);
                youreInvitation.setDuration(5000);
                youreInvitation.open();
            }
        } else {
            Notification alreadyRead = new Notification("This invitations was already read");
            alreadyRead.setPosition(Notification.Position.MIDDLE);
            alreadyRead.open();
            alreadyRead.setDuration(5000);
        }
    }

    private void updateMessage(Message message) {
        if (message != null && !message.wasRead && message.getSenderId() != backendService.getByEmail(setEmailValue()).getId()) {
            backendService.updateMessage(new Message(message.getId()
                    , message.getSenderId()
                    , message.getReceiverId()
                    , message.getSendDate()
                    , message.getMessage()
                    , true));
            updateMessageList();
            InfoLogToCreate infoLogToCreate = new InfoLogToCreate();
            infoLogToCreate.setUserId(backendService.getByEmail(setEmailValue()).getId());
            infoLogToCreate.setDate(LocalDate.now().toString());
            infoLogToCreate.setType("READ_MESSAGE");

            backendService.createInfoLog(infoLogToCreate);
        }
    }

    private void configureMessageGrid() {
        messageGrid.setSizeFull();
        messageGrid.removeColumnByKey("senderId");
        messageGrid.removeColumnByKey("receiverId");
        messageGrid.setColumns("sendDate", "message", "wasRead");
        messageGrid.addColumn(message -> {
            User user = backendService.getOne(message.getSenderId());
            return user.firstPlusLastName();
        }).setHeader("Sender");
        messageGrid.addColumn(message -> {
            User user = backendService.getOne(message.getReceiverId());
            return user.firstPlusLastName();
        }).setHeader("Receiver");
        messageGrid.setColumnReorderingAllowed(true);
        messageGrid.scrollToStart();
        messageGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void configureInvitationGrid() {
        invitationGrid.setSizeFull();
        invitationGrid.removeColumnByKey("senderId");
        invitationGrid.removeColumnByKey("receiverId");
        invitationGrid.setColumns("sendDate", "wasRead");
        invitationGrid.addColumn(invitation -> {
            User user = backendService.getOne(invitation.getSenderId());
            return user.firstPlusLastName();
        }).setHeader("Sender");
        invitationGrid.addColumn(invitationGrid -> {
            User user = backendService.getOne(invitationGrid.getReceiverId());
            return user.firstPlusLastName();
        }).setHeader("Receiver");
        invitationGrid.setColumnReorderingAllowed(true);
        invitationGrid.scrollToStart();
        invitationGrid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private void updateMessageList() {
        messageGrid.setItems(backendService.getEmailBySenderOrReceiverId(backendService.getByEmail(setEmailValue()).getId()));
    }

    private void updateInvitationList() {
        invitationGrid.setItems(backendService.getInvitationBySenderOrReceiverId(backendService.getByEmail(setEmailValue()).getId()));
    }

    private String setEmailValue() {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        return authentication;
    }
}
