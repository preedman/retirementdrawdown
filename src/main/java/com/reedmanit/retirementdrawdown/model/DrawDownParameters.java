package com.reedmanit.retirementdrawdown.model;

import com.vaadin.flow.component.textfield.NumberField;

public class DrawDownParameters {

    private NumberField inflationRate;
    private NumberField startingBalance;
    private NumberField percentageReturn;
    private NumberField yearlyWithdrawals;
    private Double inflationRateValue;
    private Double percentageReturnValue;

    public DrawDownParameters() {
        inflationRate = new NumberField();
        startingBalance = new NumberField();
        percentageReturn = new NumberField();
        yearlyWithdrawals = new NumberField();
    }

    public NumberField getInflationRate() {

        return inflationRate;
    }

    public void setInflationRate(NumberField inflationRate) {
        inflationRateValue = inflationRate.getValue() * 0.01;
        this.inflationRate = inflationRate;
    }

    public NumberField getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(NumberField startingBalance) {
        this.startingBalance = startingBalance;
    }

    public NumberField getPercentageReturn() {

        return percentageReturn;
    }

    public void setPercentageReturn(NumberField percentageReturn) {
        percentageReturnValue = percentageReturn.getValue() * 0.01;
        this.percentageReturn = percentageReturn;
    }

    public NumberField getYearlyWithdrawals() {
        return yearlyWithdrawals;
    }

    public void setYearlyWithdrawals(NumberField yearlyWithdrawals) {
        this.yearlyWithdrawals = yearlyWithdrawals;
    }

    public Double getInflationRateValue() {
        return inflationRateValue;
    }
    public Double getPercentageReturnValue() {
        return percentageReturnValue;
    }
}
