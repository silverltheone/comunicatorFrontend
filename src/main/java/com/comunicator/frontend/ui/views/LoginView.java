package com.comunicator.frontend.ui.views;

import com.comunicator.frontend.data.InfoLogToCreate;
import com.comunicator.frontend.data.User;
import com.comunicator.frontend.data.UserToCreate;
import com.comunicator.frontend.service.BackendService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Route("login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    @Autowired
    private BackendService service;

    private LoginForm login = new LoginForm();
    private Button createUser = new Button("Create User");
    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField email = new TextField("Email Adress");
    private PasswordField password = new PasswordField("Password");
    private Notification badCreateUser = new Notification("User with that email already exist");

    public LoginView(){
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        login.setAction("login");
        login.setForgotPasswordButtonVisible(false);

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getForm().setUsername("Email adress");
        login.setI18n(i18n);

        badCreateUser.setOpened(false);
        badCreateUser.setPosition(Notification.Position.BOTTOM_CENTER);
        badCreateUser.setDuration(1000);

        createUser.addClickListener(click -> creatingUser());

        add(new H2("Komunicator"), login, new HorizontalLayout(firstName, lastName), new HorizontalLayout(email, password), createUser, badCreateUser);
    }

    private void creatingUser() {
        UserToCreate userToCreate = new UserToCreate();
        userToCreate.setFirstName(firstName.getValue());
        userToCreate.setLastName(lastName.getValue());
        userToCreate.setEmail(email.getValue());
        userToCreate.setPassword(password.getValue());

        List<String>veryficationList = service.getUsers().stream()
                .map(u -> u.getEmail())
                .collect(Collectors.toList());
        if(veryficationList.contains(email.getValue())) {
            badCreateUser.setOpened(true);
            firstName.setValue("");
            lastName.setValue("");
            email.setValue("");
            password.setValue("");
        } else {
            User user = service.createUser(userToCreate);

            InfoLogToCreate infoLogToCreate = new InfoLogToCreate();
            infoLogToCreate.setUserId(user.getId());
            infoLogToCreate.setDate(LocalDate.now().toString());
            infoLogToCreate.setType("CREATING_USER");

            service.createInfoLog(infoLogToCreate);
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }
}
