package com.reedmanit.retirementdrawdown.model;

import java.util.Objects;

public class AnnualDrawdown {

    private Integer year;
    private Double openingBalance;
    private Double withdrawal;
    private Double income;
    private Double closingBalance;

    public AnnualDrawdown() {
        setYear(0);
        setOpeningBalance(0.0);
        setWithdrawal(0.0);
        setIncome(0.0);
        setClosingBalance(0.0);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(Double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public Double getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Double withdrawal) {
        this.withdrawal = withdrawal;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(Double closingBalance) {
        this.closingBalance = closingBalance;
    }

    @Override
    public boolean equals(Object o) {
        boolean value = true;
        AnnualDrawdown that = (AnnualDrawdown) o;
        if (this == o) {
            value = true;
            return value;
        }
        if (o == null || getClass() != o.getClass()) {
            value = false;
            return value;
        }

        if (!Objects.equals(year, that.year)) {
            value = false;
            return value;
        }
        if (!Objects.equals(openingBalance, that.openingBalance)) {
            value = false;
            return value;
        }

        if(!Objects.equals(withdrawal, that.withdrawal)) {
            value = false;
            return value;
        }

        if (!Objects.equals(income, that.income))  {
            value = false;
            return value;
        }
        if (!Objects.equals(closingBalance, that.closingBalance)) {
            value = false;
            return value;
        }

        return value;
    }

}
