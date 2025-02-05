/*
 * Copyright 2024 preed.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.reedmanit.retirementdrawdown.views;

import com.reedmanit.retirementdrawdown.model.DrawDownParameters;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.*;
import jakarta.annotation.security.PermitAll;

/**
 * @author preed
 */
@Route("form")
@PermitAll
public class ParameterFormView extends FormLayout implements RouterLayout {

    private NumberField startBalanceNF;  // start balance

    private NumberField percentageReturnNF;  // % returns

    private Checkbox fourPercentRule;

    private NumberField yearlyWithdrawalsNF; // how much withdraw

    private NumberField inflationRateNF; // % inflation rate

    private Button saveButton;

    private Button cancelButton;

    private DrawDownParameters drawDownParameters;

    public ParameterFormView() {

        setUpParameterView();
        drawDownParameters = new DrawDownParameters();


        saveButton.addClickListener(event -> {
            fillParameters();
            getUI().ifPresent(ui -> ui.navigate(MainAppView.class).ifPresent(

                    app -> app.saveData(drawDownParameters)));
        });


        cancelButton.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate(MainAppView.class).ifPresent(

                    app -> app.cancel()));
        });


    }

    private void fillParameters() {
        drawDownParameters.setStartingBalance(startBalanceNF);
        drawDownParameters.setPercentageReturn(percentageReturnNF);
        drawDownParameters.setYearlyWithdrawals(yearlyWithdrawalsNF);
        drawDownParameters.setInflationRate(inflationRateNF);
        drawDownParameters.setFourPercentRule(fourPercentRule.getValue());

        System.out.println(fourPercentRule.getValue());


    }

    private void setUpParameterView() {

        setStartBalanceNF(new NumberField());
        getStartBalanceNF().setRequired(true);
        getStartBalanceNF().setMin(10000.00);
        getStartBalanceNF().setMax(2000000.00);
        getStartBalanceNF().setLabel("Starting Balance");
        getStartBalanceNF().setErrorMessage("Invalid starting Balance");
        getStartBalanceNF().setRequiredIndicatorVisible(true);
        getStartBalanceNF().setClearButtonVisible(true);
        getStartBalanceNF().setPlaceholder("Enter the total $$$ retirement starting balance");
        //getStartBalanceNF().setHelperText("Valid values between 10000 and 2000000");

        getStartBalanceNF().setPrefixComponent(new Span("$"));

        this.add(getStartBalanceNF());

        setPercentageReturnNF(new NumberField());
        getPercentageReturnNF().setRequired(true);
        getPercentageReturnNF().setMin(1.00);
        getPercentageReturnNF().setMax(20.00);
        getPercentageReturnNF().setRequiredIndicatorVisible(true);
        getPercentageReturnNF().setErrorMessage("Invalid percentage Return");
        getPercentageReturnNF().setLabel("Annual % return");
        getPercentageReturnNF().setClearButtonVisible(true);
        getPercentageReturnNF().setPlaceholder("Enter the forecasted annual return");
       // getPercentageReturnNF().setHelperText("Valid values between 1% and 20%");

        getPercentageReturnNF().setSuffixComponent(new Span("%"));
        getPercentageReturnNF().setWidth("50%");
        this.add(getPercentageReturnNF());

        fourPercentRule = new Checkbox();
        fourPercentRule.setLabel("Apply Four Percent Rule");
        this.add(fourPercentRule);

        setNoFourPercentRuleWithDrawAmount();

        fourPercentRule.addValueChangeListener(event -> {
            if (fourPercentRule.getValue()) { // if checked
                setWithFourPercentWithDrawAmount();

            } else {
                resetWithdrawAmount();
            }
        });


        setInflationRateNF(new NumberField());
        getInflationRateNF().setRequired(true);
        getInflationRateNF().setLabel("Annual % inflation rate");
        getInflationRateNF().setMin(1.00);
        getInflationRateNF().setMax(20.00);
        getInflationRateNF().setRequiredIndicatorVisible(true);
        getInflationRateNF().setErrorMessage("Invalid inflation Rate");
        getInflationRateNF().setClearButtonVisible(true);
        getInflationRateNF().setSuffixComponent(new Span("%"));
        getInflationRateNF().setPlaceholder("Enter the forecasted inflation rate");
      //  getInflationRateNF().setHelperText("Valid values between 1% and 20%");
        this.add(getInflationRateNF());

        saveButton = new Button("Calculate");
        saveButton.setWidth("50%");
        cancelButton = new Button("Cancel");
        cancelButton.setWidth("50%");

        this.add(saveButton, cancelButton);


        this.setResponsiveSteps(
                // Use one column by default
                new ResponsiveStep("0", 1));


    }

    public NumberField getStartBalanceNF() {
        return startBalanceNF;
    }

    public void setStartBalanceNF(NumberField startBalanceNF) {
        this.startBalanceNF = startBalanceNF;
    }

    public NumberField getPercentageReturnNF() {
        return percentageReturnNF;
    }

    public void setPercentageReturnNF(NumberField percentageReturnNF) {
        this.percentageReturnNF = percentageReturnNF;
    }

    public NumberField getYearlyWithdrawalsNF() {
        return yearlyWithdrawalsNF;
    }

    public void setYearlyWithdrawalsNF(NumberField yearlyWithdrawalsNF) {
        this.yearlyWithdrawalsNF = yearlyWithdrawalsNF;
    }

    public NumberField getInflationRateNF() {
        return inflationRateNF;
    }

    public void setInflationRateNF(NumberField inflationRateNF) {
        this.inflationRateNF = inflationRateNF;
    }

    private void setWithFourPercentWithDrawAmount() {


        NumberField withdrawAmount = new NumberField();
        withdrawAmount.setValue(getStartBalanceNF().getValue() * 0.04);
        var ui = UI.getCurrent();
        ui.access(() -> {

            this.getYearlyWithdrawalsNF().setValue(withdrawAmount.getValue());
            this.getYearlyWithdrawalsNF().setEnabled(false);
            ui.push();
        });




    }

    private void resetWithdrawAmount() {
        var ui = UI.getCurrent();
        ui.access(() -> {
            getYearlyWithdrawalsNF().setEnabled(true);
            getYearlyWithdrawalsNF().setValue(0.0);
            ui.push();
        });
    }

    private void setNoFourPercentRuleWithDrawAmount() {
        setYearlyWithdrawalsNF(new NumberField());
        getYearlyWithdrawalsNF().setRequired(true);
        getYearlyWithdrawalsNF().setLabel("Annual withdrawal");
        getYearlyWithdrawalsNF().setMin(500.00);
        getYearlyWithdrawalsNF().setMax(1000000.00);
        getYearlyWithdrawalsNF().setRequiredIndicatorVisible(true);
        getYearlyWithdrawalsNF().setErrorMessage("Invalid yearly Withdrawal");
        getYearlyWithdrawalsNF().setClearButtonVisible(true);
        getYearlyWithdrawalsNF().setPrefixComponent(new Span("$"));
        getYearlyWithdrawalsNF().setPlaceholder("Enter the $$$ yearly withdrawal");
       // getYearlyWithdrawalsNF().setHelperText("Valid values between 500 and 1000000");
        this.add(getYearlyWithdrawalsNF());
    }


}
