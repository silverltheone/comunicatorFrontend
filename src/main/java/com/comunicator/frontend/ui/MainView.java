package com.comunicator.frontend.ui;

import com.comunicator.frontend.service.BackendService;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    private final CreateUserForm form;
    private BackendService backendService;

    public MainView(BackendService backendService) {
        this.backendService = backendService;
        form = new CreateUserForm();
        form.addListener(CreateUserForm.CreateEvent.class, this::createUser);

        Div content = new Div(form);
        content.setSizeFull();

        add(content);
    }

    private void createUser(CreateUserForm.CreateEvent evt) {
        backendService.createUser(evt.getUser());
    }
}
