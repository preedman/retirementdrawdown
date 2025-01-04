package com.reedmanit.retirementdrawdown.model;

import com.vaadin.flow.component.textfield.NumberField;

public class DrawDownParameters {

    private NumberField inflationRate;
    private NumberField startingBalance;
    private NumberField percentageReturn;
    private NumberField yearlyWithdrawals;
    private Double inflationRateValue;
    private Double percentageReturnValue;
    private Boolean fourPercentRule;

    public DrawDownParameters() {
        inflationRate = new NumberField();
        startingBalance = new NumberField();
        percentageReturn = new NumberField();
        yearlyWithdrawals = new NumberField();
        fourPercentRule = false;
    }

    public NumberField getInflationRate() {

        return inflationRate;
    }

    public void setFourPercentRule(boolean fourPercentRule) {
        this.fourPercentRule = fourPercentRule;
    }

    public Boolean getFourPercentRule() {
        return fourPercentRule;
    }

    public void setInflationRate(NumberField inflationRate) {
        inflationRateValue = inflationRate.getValue() * 0.01;
        this.inflationRate = inflationRate;
    }

    public NumberField getStartingBalance() {
        return startingBalance;
    }

    public String getStartingBalanceAsString() {
        return String.valueOf(startingBalance.getValue());
    }
    public String getInflationRateAsString() {
        return String.valueOf(inflationRate.getValue());
    }
    public String getPercentageReturnAsString() {
        return String.valueOf(percentageReturn.getValue());
    }
    public String getYearlyWithdrawalsAsString() {
        return String.valueOf(yearlyWithdrawals.getValue());
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
