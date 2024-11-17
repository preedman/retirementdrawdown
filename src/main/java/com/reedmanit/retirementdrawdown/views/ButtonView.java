package com.reedmanit.retirementdrawdown.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ButtonView extends HorizontalLayout
{
    private Button calculateBTN;
    private Button whatIfBTN;

    public ButtonView () {
        calculateBTN = new Button("Calculate");
        whatIfBTN = new Button("What if");
        setUp();

    }

    public Button getCalculateBTN() {
        return calculateBTN;
    }

    public Button getWhatIfBTN() {
        return whatIfBTN;
    }

    private void setUp() {
        whatIfBTN.setEnabled(false);
        this.getStyle().set("border", "1px solid");
        this.setWidthFull();
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        this.add(calculateBTN, whatIfBTN);
    }
}
