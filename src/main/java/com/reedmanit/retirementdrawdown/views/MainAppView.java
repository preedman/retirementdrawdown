package com.reedmanit.retirementdrawdown.views;

import com.reedmanit.retirementdrawdown.model.DrawDownParameters;
import com.reedmanit.retirementdrawdown.service.DownloadDataService;
import com.reedmanit.retirementdrawdown.service.DrawDownService;
import com.reedmanit.retirementdrawdown.service.SecurityService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.textfield.NumberField;

import com.vaadin.flow.router.*;
import jakarta.annotation.security.PermitAll;

import java.io.FileNotFoundException;

@Route("")
@PermitAll
public class MainAppView extends AppLayout {
    private Button parametersBTN;
    private Button logoutBTN;
    private Button showParametersBTN;
    private Button downloadBTN;
    private Anchor helpAnchor;
    private Button helpBTN;

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
    private DownloadDataService downloadDataService;



    public MainAppView(SecurityService securityService) {


        this.securityService = securityService;

        //System.out.println(System.getenv('USER'));



        initaliseParameters();

        HorizontalLayout navigation = getNavigation();
        navigation.getElement();
        addToNavbar(navigation);

        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.BOTH);



        service = new DrawDownService(parameters);


        gridView = new DrawdownGridView();
        gridView.setTheGrid(service.calculateListOfDrawDowns());


        this.setContent(gridView.getTheGrid());
        getElement().getStyle().set("height", "100%");
        gridView.getTheGrid().setHeight("100%");

        this.addToDrawer(scroller);




        parametersBTN.addClickListener(event -> {

            UI.getCurrent().navigate(ParameterFormView.class);

        });

        downloadBTN.addClickListener(event -> {
            try {

                downloadDataService = new DownloadDataService(service.getListOfDrawDowns());
                downloadDataService.download();
                Notification notification = Notification.show("Download completed!");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

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


        gridView.setTheGrid(service.calculateListOfDrawDowns());

        showNotificationYears(service.getNumberOfYears());



        this.setContent(gridView.getTheGrid());
    }

    public void cancel() { // called by the form
        initaliseParameters();
        service = new DrawDownService(parameters);


        gridView = new DrawdownGridView();
        gridView.setTheGrid(service.calculateListOfDrawDowns());


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

        parametersBTN = new Button(new Icon(VaadinIcon.PLUS));
        parametersBTN.addThemeVariants(ButtonVariant.LUMO_ICON);
        parametersBTN.setTooltipText("Enter Drawdown Parameters");

        showParametersBTN = new Button(new Icon(VaadinIcon.INFO));
        showParametersBTN.addThemeVariants(ButtonVariant.LUMO_ICON);
        showParametersBTN.setTooltipText("Show Drawdown Parameters");



        logoutBTN = new Button(new Icon(VaadinIcon.EXIT));
        logoutBTN.addThemeVariants(ButtonVariant.LUMO_ICON);
        logoutBTN.setTooltipText("Logout of App");

        downloadBTN = new Button(new Icon(VaadinIcon.DOWNLOAD));
        downloadBTN.addThemeVariants(ButtonVariant.LUMO_ICON);
        downloadBTN.setTooltipText("Download");
        downloadBTN.setEnabled(false);  // not turned on at the moment - future feature

        helpBTN = new Button(new Icon(VaadinIcon.QUESTION));
        helpBTN.addThemeVariants(ButtonVariant.LUMO_ICON);
        helpBTN.setTooltipText("Help");

        helpAnchor = new Anchor("https://github.com/preedman/retirementdrawdown/wiki",
                helpBTN);

        parametersBTN.setSizeUndefined();
        showParametersBTN.setSizeUndefined();
        logoutBTN.setSizeUndefined();
        downloadBTN.setSizeUndefined();

        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);


        navigation.add(parametersBTN, showParametersBTN, downloadBTN,helpAnchor,logoutBTN);


        scroller.setContent(navigation);

        return navigation;
    }


}
