package com.comunicator.frontend.ui.views;

import com.comunicator.frontend.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "NewsView", layout = MainLayout.class)
public class NewsView extends VerticalLayout {

    public NewsView() {
        add(new Button("Data"), new Button("Pogoda"));
    }
}
