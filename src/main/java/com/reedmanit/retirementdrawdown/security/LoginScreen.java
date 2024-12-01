package com.reedmanit.retirementdrawdown.security;

import com.reedmanit.retirementdrawdown.model.UserRepository;
import com.reedmanit.retirementdrawdown.service.AuthService;
import com.reedmanit.retirementdrawdown.views.MainAppView;
import com.reedmanit.retirementdrawdown.views.MainView;
import com.reedmanit.retirementdrawdown.views.ParameterFormView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.login.LoginOverlay;

import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.theme.lumo.LumoUtility;

@Route(value = "login")
@PageTitle("login")
public class LoginScreen extends LoginOverlay
{
    private UserRepository userRepository;

    public LoginScreen(AuthService authService) {
        ;
        userRepository = new UserRepository();
        this.setTitle("Retirement Drawdown");
        this.setDescription("Calculator");
        Paragraph text = new Paragraph("ReedmanIT Safe Software");
        text.addClassName(LumoUtility.TextAlignment.CENTER);
        this.getFooter().add(text);
        this.setOpened(true);
        this.addLoginListener(event -> {

            String user = event.getUsername();
            String password = event.getPassword();
            if (authService.Authenticate(user, password)) {

                RouteConfiguration configuration =
                        RouteConfiguration.forSessionScope();
                configuration.setRoute("user", MainAppView.class);
                configuration.setRoute("admin", ParameterFormView.class);


               // RouteConfiguration.forSessionScope().setRoute(
               //         "user", MainAppView.class
                 //       "user", MainAppView.class, ParameterFormView.class

                //);
                UI.getCurrent().navigate(MainAppView.class);
            } else {
                this.setError(true);
            }



            //System.out.println("Login clicked");
        });
    }

}
