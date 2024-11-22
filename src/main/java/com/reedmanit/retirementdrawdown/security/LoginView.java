package com.reedmanit.retirementdrawdown.security;

import com.reedmanit.retirementdrawdown.model.User;
import com.reedmanit.retirementdrawdown.model.UserRepository;
import com.reedmanit.retirementdrawdown.service.AuthService;
import com.reedmanit.retirementdrawdown.views.MainView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;


//import java.awt.*;

//@Route(value = "login")
//@PageTitle("Login")

public class LoginView extends VerticalLayout {

    private UserRepository userRepository;

    public LoginView(AuthService authService) {
        var username = new TextField("Username");
        var password = new PasswordField("Password");
        userRepository = new UserRepository();

        this.setWidthFull();
        this.getStyle().set("border", "1px solid");
        this.setJustifyContentMode(JustifyContentMode.CENTER);


        this.add(
                new H1("Welcome to Retirement Drawdown"),
                username,
                password,
                new Button("Login", event -> {
                        if (authService.Authenticate(username.getValue(), password.getValue())) {
                            System.out.println("Logged in");
                            RouteConfiguration.forSessionScope().setRoute(
                                    "user",MainView.class
                            );
                            UI.getCurrent().navigate(MainView.class);

                        } else {
                            Notification.show("Incorrect username or password");
                        }

                })

        );
    }

}
