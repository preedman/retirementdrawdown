package com.reedmanit.retirementdrawdown.views;

import com.reedmanit.retirementdrawdown.model.DrawDownParameters;
import com.reedmanit.retirementdrawdown.service.DrawDownService;
import com.reedmanit.retirementdrawdown.service.SecurityService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.textfield.NumberField;

import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

@Route("")
@PermitAll
public class MainAppView extends AppLayout {
    private Button parametersBTN;
    private Button logoutBTN;
    private Button showParametersBTN;

    private RouterLink parametersLink;
    private RouterLink showParametersLink;
    private RouterLink logoutLink;

    private DrawDownService service;
    private DrawdownGridView gridView;
    private DrawDownParameters parameters;
    private HorizontalLayout navigation;
    private Details parametersDetails;
    private Span startBalanceInfo;
    private ParametersDialogView parametersDialogView;
    private final SecurityService securityService;


    public MainAppView(SecurityService securityService) {


        this.securityService = securityService;
        initaliseParameters();

        HorizontalLayout navigation = getNavigation();
        navigation.getElement();
        addToNavbar(navigation);

        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.BOTH);



        service = new DrawDownService(parameters);


        gridView = new DrawdownGridView();
        gridView.setTheGrid(service.getListOfDrawDowns());


        this.setContent(gridView.getTheGrid());
        getElement().getStyle().set("height", "100%");
        gridView.getTheGrid().setHeight("100%");

        this.addToDrawer(scroller);




        parametersBTN.addClickListener(event -> {

            UI.getCurrent().navigate(ParameterFormView.class);

        });
        logoutBTN.addClickListener(e -> {
            UI.getCurrent().getPage().setLocation("login");
            securityService.logout();
        });
        showParametersBTN.addClickListener(event -> {
            parametersDialogView = new ParametersDialogView(parameters);
            parametersDialogView.open();
        });


    }

    public void saveData(DrawDownParameters theParameters) { // called by the form

        parameters = theParameters;
        service = new DrawDownService(theParameters);


        gridView.setTheGrid(service.getListOfDrawDowns());

        showNotificationYears(service.getNumberOfYears());

        this.setContent(gridView.getTheGrid());
    }

    public void cancel() { // called by the form
        initaliseParameters();
        service = new DrawDownService(parameters);


        gridView = new DrawdownGridView();
        gridView.setTheGrid(service.getListOfDrawDowns());


        this.setContent(gridView.getTheGrid());
    }

    private void showNotificationYears(Integer numberOfYears) {
        Notification notification = Notification
                .show("Number of Withdrawal Years " + numberOfYears, 5000, Notification.Position.BOTTOM_START);
        notification.addThemeVariants(NotificationVariant.LUMO_PRIMARY);

    }

    private void initaliseParameters() {
        parameters = new DrawDownParameters();

        NumberField startBalance = new NumberField("Start Balance");
        startBalance.setValue(0.0);
        NumberField inflationRate = new NumberField("Inflation Rate");
        inflationRate.setValue(0.0);
        NumberField percentageReturn = new NumberField("Percentage Return");
        percentageReturn.setValue(0.0);
        NumberField yearlyWithdraw = new NumberField("Yearly Withdraw");
        yearlyWithdraw.setValue(0.0);


        parameters.setStartingBalance(startBalance);
        parameters.setInflationRate(inflationRate);
        parameters.setPercentageReturn(percentageReturn);
        parameters.setYearlyWithdrawals(yearlyWithdraw);
    }

    private HorizontalLayout getNavigation() {
        navigation = new HorizontalLayout();

        parametersBTN = new Button("Enter Drawdown");
        showParametersBTN = new Button("Show Drawdown");
        logoutBTN = new Button("Logout");


        logoutBTN.addThemeVariants(ButtonVariant.LUMO_SMALL);

        parametersBTN.setSizeUndefined();
        showParametersBTN.setSizeUndefined();
        logoutBTN.setSizeUndefined();

        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);


        navigation.add(parametersBTN, showParametersBTN, logoutBTN);


        scroller.setContent(navigation);

        return navigation;
    }


}
