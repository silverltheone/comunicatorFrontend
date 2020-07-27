package com.comunicator.frontend.ui.forms;

import com.comunicator.frontend.MainLayout;
import com.comunicator.frontend.client.BackendClient;
import com.comunicator.frontend.data.CreatedUser;
import com.comunicator.frontend.data.LoggedUser;
import com.comunicator.frontend.service.BackendService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.Getter;

@Getter
@Route(value = "Login", layout = MainLayout.class)
public class LoginForm extends FormLayout {

    private BackendService service;
    private LoggedUser loggedUser = new LoggedUser();

    TextField email = new TextField("Email");
    TextField password = new TextField("Password");

    Button create = new Button("Login");
    Button close = new Button("Cancel");

    public LoginForm(BackendService service) {
        this.service = service;

        create.addClickListener(click -> {
            loggedUser = service.getByEmailAndPassword(email.getValue(), password.getValue());
            Notification logged_by = new Notification("Logged By");
            logged_by.setText("Logged user: " + loggedUser.getFirstName() + " " + loggedUser.getLastName());
            logged_by.setDuration(3000);
            logged_by.setPosition(Notification.Position.MIDDLE);
            logged_by.open();
        });

        add(
                email,
                password,
                createButtonsLayout()
        );
    }

    private Component createButtonsLayout() {
        create.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        create.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(create, close);
    }
}
