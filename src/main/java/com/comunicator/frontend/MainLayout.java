package com.comunicator.frontend;

import com.comunicator.frontend.ui.forms.CreateUserForm;
import com.comunicator.frontend.ui.forms.LoginForm;
import com.comunicator.frontend.ui.views.MessageArchiveView;
import com.comunicator.frontend.ui.views.NewsView;
import com.comunicator.frontend.ui.views.UserView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    private CreateUserForm createUserForm;

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createDrawer() {
        RouterLink messagesLink = new RouterLink("Messages", MessageArchiveView.class);
        messagesLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink userVieLink = new RouterLink("Userview", UserView.class);
        userVieLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink newsLink = new RouterLink("Newsview", NewsView.class);
        newsLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(userVieLink, messagesLink, newsLink));
    }

    private void createHeader() {

        RouterLink createUserLink = new RouterLink("CreateUser", CreateUserForm.class);
        RouterLink loginLink = new RouterLink("Login", LoginForm.class);
        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), loginLink, createUserLink);
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        addToNavbar(header);
    }
}
