package com.comunicator.frontend.ui.views;

import com.comunicator.frontend.MainLayout;
import com.comunicator.frontend.service.BackendService;
import com.comunicator.frontend.ui.forms.LoginForm;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "Userview", layout = MainLayout.class)
@PageTitle("Komunicator | Userview")
public class UserView extends VerticalLayout {

    private BackendService backendService;
    private Button createMessage = new Button("Send Message");
    private TextArea message = new TextArea("Message");

    public UserView(BackendService backendService) {
        this.backendService = backendService;

        message.setValueChangeMode(ValueChangeMode.LAZY);
        message.setClearButtonVisible(true);
        message.setPlaceholder("Message");

        add(message, createMessage);
    }
}
