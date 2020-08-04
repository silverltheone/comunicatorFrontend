package com.comunicator.frontend.ui.views;

import com.comunicator.frontend.data.InfoLogToCreate;
import com.comunicator.frontend.data.InvitationToCreate;
import com.comunicator.frontend.data.MessageToCreate;
import com.comunicator.frontend.data.User;
import com.comunicator.frontend.service.BackendService;
import com.comunicator.frontend.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = "Userview", layout = MainLayout.class)
@PageTitle("Komunicator | Userview")
public class UserView extends VerticalLayout {

    private BackendService service;

    private ComboBox<User> comboBox = new ComboBox<>();
    private Button createMessage = new Button("Send Message");
    private TextField sender = new TextField("Sender");
    private TextField receiver = new TextField("Receiver");
    private TextArea message = new TextArea("Message");
    private Notification noReceiver = new Notification("You dont select receiver!!!");
    private EmailField invitationEmail = new EmailField();
    private Button sendInvitation = new Button("Send Invitation");


    public UserView(BackendService service) {
        this.service = service;

        noReceiver.setOpened(false);
        noReceiver.setPosition(Notification.Position.BOTTOM_CENTER);
        noReceiver.setDuration(1000);
        receiver.setInvalid(true);

        List<User> friendsList = getFriends();
        comboBox.setItemLabelGenerator(User::firstPlusLastName);
        comboBox.setItems(friendsList);
        comboBox.addValueChangeListener(event -> {
            if (event.getValue() == null) {
                receiver.setValue("No friend selected");
            } else {
                receiver.setValue(event.getValue().firstPlusLastName());
                receiver.setInvalid(false);
            }
        });

        receiver.setReadOnly(true);

        message.setValueChangeMode(ValueChangeMode.LAZY);
        message.setClearButtonVisible(true);
        message.setPlaceholder("Message");

        sender.setReadOnly(true);
        sender.setValue(service.getByEmail(setEmailValue()).getFirstName());

        createMessage.addClickListener(click -> sendMessage());

        sendInvitation.addClickListener((click -> sendInvitation()));

        add(new HorizontalLayout(sender, receiver), message, new HorizontalLayout(createMessage, comboBox), new HorizontalLayout(invitationEmail, sendInvitation), noReceiver);
    }

    private void sendInvitation() {
        if(!invitationEmail.getValue().isEmpty()) {
            List<String> emailAdressesInBase = service.getUsers().stream()
                    .map(u ->u.getEmail())
                    .collect(Collectors.toList());
            if(emailAdressesInBase.contains(invitationEmail.getValue())) {
                InvitationToCreate invitationToCreate = new InvitationToCreate();
                invitationToCreate.setSenderId(service.getByEmail(setEmailValue()).getId());
                invitationToCreate.setReceiverId(service.getByEmail(invitationEmail.getValue()).getId());
                invitationToCreate.setSendDate(LocalDate.now().toString());
                invitationToCreate.setWasRead(false);

                service.createInvitation(invitationToCreate);

                InfoLogToCreate infoLogToCreate = new InfoLogToCreate();
                infoLogToCreate.setUserId(invitationToCreate.getSenderId());
                infoLogToCreate.setDate(LocalDate.now().toString());
                infoLogToCreate.setType("SEND_INVITATION");

                service.createInfoLog(infoLogToCreate);
            } else {
                Notification wrongEmail = new Notification("There's no user with this email");
                wrongEmail.setPosition(Notification.Position.MIDDLE);
                wrongEmail.setDuration(5000);
                wrongEmail.open();
            }
        } else {
            Notification noEmail = new Notification("C'mon give me email adress");
            noEmail.setPosition(Notification.Position.MIDDLE);
            noEmail.setDuration(5000);
            noEmail.open();
        }
    }

    private void sendMessage() {
        if(receiver.isInvalid()) {
            noReceiver.setOpened(true);
        } else {
            MessageToCreate messageToCreate = new MessageToCreate();
            messageToCreate.setSenderId(service.getByEmail(setEmailValue()).getId());
            messageToCreate.setReceiverId(service.getByEmail(comboBox.getValue().getEmail()).getId());
            messageToCreate.setSendDate(LocalDate.now().toString());
            messageToCreate.setMessage(message.getValue());
            messageToCreate.setWasRead(false);

            service.sendMessage(messageToCreate);

            InfoLogToCreate infoLogToCreate = new InfoLogToCreate();
            infoLogToCreate.setUserId(messageToCreate.getSenderId());
            infoLogToCreate.setDate(LocalDate.now().toString());
            infoLogToCreate.setType("CREATING_MESSAGE");

            service.createInfoLog(infoLogToCreate);
        }
    }

    private List<User> getFriends() {
        User loggedUser = service.getByEmail(setEmailValue());
        List<User> friendsList = loggedUser.getFriendsId().stream()
                .map(i -> service.getOne(i))
                .collect(Collectors.toList());
        return friendsList;
    }

    private String setEmailValue() {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        return authentication;
    }
}
