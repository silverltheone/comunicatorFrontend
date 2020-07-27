package com.comunicator.frontend.ui.forms;

import com.comunicator.frontend.MainLayout;
import com.comunicator.frontend.data.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.Registration;

@Route(value = "CreateUser", layout = MainLayout.class)
public class CreateUserForm extends FormLayout {

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField email = new TextField("Email");
    TextField password = new TextField("Password");

    Button create = new Button("Create account");
    Button close = new Button("Cancel");

    Binder<User> binder = new Binder<>(User.class);

    public CreateUserForm() {

        binder.bindInstanceFields(this);

        add(
                firstName,
                lastName,
                email,
                password,
                createButtonsLayout()
        );
    }

    public void setUser(User user) {
        binder.setBean(user);
    }

    private Component createButtonsLayout() {
        create.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        create.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        create.addClickListener(click -> validateAndCreate());
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> create.setEnabled(binder.isValid()));

        return new HorizontalLayout(create, close);
    }

    private void validateAndCreate() {
        if(binder.isValid()) {
            fireEvent(new CreateEvent(this, binder.getBean()));
        }
    }

    //Events
    public static abstract class CreateUserFormEvent extends ComponentEvent<CreateUserForm> {
        private User user;

        protected CreateUserFormEvent(CreateUserForm source, User user) {
            super(source, false);
            this.user = user;
        }

        public User getUser() {
            return user;
        }
    }

    public static class CreateEvent extends CreateUserFormEvent {
        CreateEvent(CreateUserForm source, User user) {
            super(source, user);
        }
    }

    public static class CloseEvent extends CreateUserFormEvent {
        CloseEvent(CreateUserForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
