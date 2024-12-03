package com.reedmanit.retirementdrawdown.views;

import com.reedmanit.retirementdrawdown.model.AnnualDrawdown;
import com.reedmanit.retirementdrawdown.model.DrawDownParameters;
import com.reedmanit.retirementdrawdown.views.AppGridView;
import com.reedmanit.retirementdrawdown.service.DrawDownService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;


public class MainAppView extends AppLayout {
    private Button parametersBTN;
    private Button logoutBTN;
    private DrawDownService service;
    private DrawdownGridView gridView;
    private DrawDownParameters parameters;

    public MainAppView() {
        H1 title = new H1("Retirement Drawdown");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "var(--lumo-space-m)");
        HorizontalLayout navigation = getNavigation();
        navigation.getElement();
        addToNavbar(title, navigation);

        Scroller scroller = new Scroller();
        scroller.setScrollDirection(Scroller.ScrollDirection.BOTH);
        //scroller.setClassName(LumoUtility.Padding.SMALL);

        //   AppGridView myGridView = new AppGridView();

        initaliseParameters();

        service = new DrawDownService(parameters);


        gridView = new DrawdownGridView();
        gridView.setTheGrid(service.getListOfDrawDowns());


        this.setContent(gridView.getTheGrid());

        this.addToDrawer(scroller);


        //  this.setContent(myGridView);
        getElement().getStyle().set("height", "100%");
        // gridView.getTheGrid().setHeight("100%");
        // gridView.getTheGrid().addThemeVariants(GridVariant.LUMO_NO_BORDER);

        parametersBTN.addClickListener(event -> {
            //  RouteConfiguration.forSessionScope().setRoute("admin", ParameterFormView.class);
            UI.getCurrent().navigate(ParameterFormView.class);
        });
        logoutBTN.addClickListener(e -> {
            UI.getCurrent().getPage().setLocation("login");
            VaadinSession.getCurrent().close();
        });


    }

    public void saveData(DrawDownParameters theParameters) { // called by the form
        //System.out.println("Back from form " + data);
        service = new DrawDownService(theParameters);
        gridView.setTheGrid(service.getListOfDrawDowns());
        this.setContent(gridView.getTheGrid());
    }

    public void cancel() { // called by the form
        initaliseParameters();
        service = new DrawDownService(parameters);


        gridView = new DrawdownGridView();
        gridView.setTheGrid(service.getListOfDrawDowns());


        this.setContent(gridView.getTheGrid());
    }

    private void initaliseParameters() {
        parameters = new DrawDownParameters();

        NumberField startBalance = new NumberField("Start Balance");
        startBalance.setValue(0.0);
        NumberField inflationRate = new NumberField("Inflation Rate");
        inflationRate.setValue(0.03);
        NumberField percentageReturn = new NumberField("Percentage Return");
        percentageReturn.setValue(0.05);
        NumberField yearlyWithdraw = new NumberField("Yearly Withdraw");
        yearlyWithdraw.setValue(0.0);


        parameters.setStartingBalance(startBalance);
        parameters.setInflationRate(inflationRate);
        parameters.setPercentageReturn(percentageReturn);
        parameters.setYearlyWithdrawals(yearlyWithdraw);
    }

    private HorizontalLayout getNavigation() {
        HorizontalLayout navigation = new HorizontalLayout();
        navigation.addClassNames(LumoUtility.JustifyContent.CENTER,
                LumoUtility.Gap.SMALL, LumoUtility.Height.MEDIUM,
                LumoUtility.Width.FULL);
        parametersBTN = new Button("Parameters");
        logoutBTN = new Button("Logout");
        navigation.add(parametersBTN, logoutBTN);
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

}
