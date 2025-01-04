package com.reedmanit.retirementdrawdown.views;

import com.reedmanit.retirementdrawdown.model.DrawDownParameters;
import com.reedmanit.retirementdrawdown.service.DrawDownService;
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

import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;


public class MainAppView extends AppLayout {
    private Button parametersBTN;
    private Button logoutBTN;
    private Button showParametersBTN;
    private DrawDownService service;
    private DrawdownGridView gridView;
    private DrawDownParameters parameters;
    private HorizontalLayout navigation;
    private Details parametersDetails;
    private Span startBalanceInfo;
    private ParametersDialogView parametersDialogView;


    public MainAppView() {
       // H1 title = new H1("Retirement Drawdown");
        initaliseParameters();
       // title.getStyle().set("font-size", "var(--lumo-font-size-l)")
        //       .set("margin", "var(--lumo-space-m)");
        HorizontalLayout navigation = getNavigation();
        navigation.getElement();
        addToNavbar(navigation);

        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.BOTH);
        //scroller.setClassName(LumoUtility.Padding.SMALL);

        //   AppGridView myGridView = new AppGridView();



        service = new DrawDownService(parameters);


        gridView = new DrawdownGridView();
        gridView.setTheGrid(service.getListOfDrawDowns());


        this.setContent(gridView.getTheGrid());
        getElement().getStyle().set("height", "100%");
        gridView.getTheGrid().setHeight("100%");

        this.addToDrawer(scroller);


        //  this.setContent(myGridView);

        // gridView.getTheGrid().setHeight("100%");
        // gridView.getTheGrid().addThemeVariants(GridVariant.LUMO_NO_BORDER);

        parametersBTN.addClickListener(event -> {
            //  RouteConfiguration.forSessionScope().setRoute("admin", ParameterFormView.class);
              UI.getCurrent().navigate(ParameterFormView.class);
           // UI.getCurrent().navigate(ParameterFormView.class,"data");
        });
        logoutBTN.addClickListener(e -> {
            UI.getCurrent().getPage().setLocation("login");
            VaadinSession.getCurrent().close();
        });
        showParametersBTN.addClickListener(event -> {
            parametersDialogView = new ParametersDialogView(parameters);
            parametersDialogView.open();
        });


    }

    public void saveData(DrawDownParameters theParameters) { // called by the form
        //System.out.println("Back from form " + data);
        parameters = theParameters;
        service = new DrawDownService(theParameters);

        //parametersDetails.setContent(createParametersList(theParameters));
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
       // notification.setPosition(Notification.Position.MIDDLE);
        //notification.setDuration(0);
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
       // navigation.addClassNames(LumoUtility.JustifyContent.START,
       //         LumoUtility.Gap.SMALL, LumoUtility.Height.SMALL,
       //         LumoUtility.Width.SMALL);
        parametersBTN = new Button("Enter Parameters");
        showParametersBTN = new Button("Show Parameters");
        logoutBTN = new Button("Logout");

       parametersBTN.addThemeVariants(ButtonVariant.LUMO_SMALL);
        showParametersBTN.addThemeVariants(ButtonVariant.LUMO_SMALL);
       logoutBTN.addThemeVariants(ButtonVariant.LUMO_SMALL);

        parametersBTN.setSizeUndefined();
        showParametersBTN.setSizeUndefined();
        logoutBTN.setSizeUndefined();

        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.HORIZONTAL);


       // startBalance = new Span("Start Balance");
       // startBalance.getElement().getThemeList().add("badge");
        //startBalanceInfo = new Span("Start Balance " + parameters.getStartingBalanceAsString());

      //  parametersDetails = new Details("Parameters",createParametersList(parameters));
      //  parametersDetails.setOpened(false);
       // parametersDetails.addThemeVariants(DetailsVariant.SMALL);
        navigation.add(parametersBTN, showParametersBTN, logoutBTN);

      // navigation.setAlignSelf();

        scroller.setContent(navigation);
        // navigation.add(createLink("Parameters"), createLink("Orders"),
        //         createLink("Customers"), createLink("Products"));
        return navigation;
    }


    private RouterLink createLink(String viewName) {
        RouterLink link = new RouterLink();
        link.add(viewName);

        if (viewName.equals("Parameters")) {
            link.setRoute(ParameterFormView.class);
        }
        // Demo has no routes
        // link.setRoute(viewClass.java);

        link.addClassNames(LumoUtility.Display.FLEX,
                LumoUtility.AlignItems.CENTER,
                LumoUtility.Padding.Horizontal.MEDIUM,
                LumoUtility.TextColor.SECONDARY, LumoUtility.FontWeight.MEDIUM);
        link.getStyle().set("text-decoration", "none");

        return link;
    }

    private UnorderedList createParametersList(DrawDownParameters theParameters) {
        UnorderedList list = new UnorderedList();
        list.add("Starting Balance " + theParameters.getStartingBalanceAsString() + " ");
        list.add("Inflation Rate " + theParameters.getInflationRateAsString() + " ");
        list.add("Percentage Return " + theParameters.getPercentageReturnAsString() + " ");
        list.add("Withdrawals " + theParameters.getYearlyWithdrawalsAsString() + " ");
        return list;

    }

}
