package com.comunicator.frontend.ui;

import com.comunicator.frontend.ui.views.MessageView;
import com.comunicator.frontend.ui.views.UserView;
import com.comunicator.frontend.ui.views.WeatherView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createDrawer() {

        RouterLink messagesLink = new RouterLink("Messages", MessageView.class);
        messagesLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink userVieLink = new RouterLink("Userview", UserView.class);
        userVieLink.setHighlightCondition(HighlightConditions.sameLocation());

        RouterLink newsLink = new RouterLink("Weather", WeatherView.class);
        newsLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(userVieLink, messagesLink, newsLink));
    }

    private void createHeader() {
        H2 logo = new H2("Komunicator");
        Anchor logOut = new Anchor("/logout", "Log out");
        DrawerToggle drawerToggle = new DrawerToggle();

        HorizontalLayout header = new HorizontalLayout(drawerToggle, logo,logOut);
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");

        addToNavbar(header);
    }
}
