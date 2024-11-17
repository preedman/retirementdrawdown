package com.reedmanit.retirementdrawdown.model;

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


}
