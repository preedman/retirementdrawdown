package com.reedmanit.retirementdrawdown.views;

import com.vaadin.flow.component.Text;
//import com.vaadin.flow.component.charts.model.Tooltip;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.shared.Tooltip;



public class CalculatorInformation extends HorizontalLayout {

    private H1 Header;
    private Text informationText;
    private Text headerText;
    private Tooltip tooltip;

    public CalculatorInformation() {
        Header = new H1("Retirement Draw Down");
        //tooltip = new Tooltip();
        Tooltip tooltip = Tooltip.forComponent(Header)
                .withText("This calculator will model how long your retirement savings will last after you retire")
                .withPosition(Tooltip.TooltipPosition.START_TOP);


        // informationText = new Text("This calculator will model how long your retirement savings will last after you retire");
        this.setWidthFull();
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        //this.setSpacing(true);
        add(Header);
    }

}
