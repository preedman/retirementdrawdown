package com.reedmanit.retirementdrawdown.views;

import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class ButtonView extends HorizontalLayout
{
    private Button calculateBTN;
    private Button whatIfBTN;
    private Button logoutBTN;
    private NativeLabel label;

    public ButtonView () {
        calculateBTN = new Button("Calculate");
        whatIfBTN = new Button("What if");
        logoutBTN = new Button("Logout");
        label = new NativeLabel();
        setUp();

    }

    public Button getCalculateBTN() {
        return calculateBTN;
    }

    public Button getWhatIfBTN() {
        return whatIfBTN;
    }

    public Button getLogoutBTN() {
        return logoutBTN;
    }

    public void setLabel (String label) {
        this.label.setText(label);
    }

    private void setUp() {
        whatIfBTN.setEnabled(false);
        this.getStyle().set("border", "1px solid");
        this.setWidthFull();
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        this.add(calculateBTN, whatIfBTN, logoutBTN, label);
    }
}
