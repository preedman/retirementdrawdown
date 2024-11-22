package com.reedmanit.retirementdrawdown.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

//@Route("logout")
public class LogoutView extends VerticalLayout {

    public LogoutView() {
        UI.getCurrent().getPage().setLocation("login");
        VaadinSession.getCurrent().close();

    }
}
