package com.reedmanit.retirementdrawdown.service;

import com.reedmanit.retirementdrawdown.model.AnnualDrawdown;
import com.reedmanit.retirementdrawdown.model.DrawDownParameters;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DrawDownService {

    private DrawDownParameters parameters;
    private List<AnnualDrawdown> listOfDrawDowns;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public DrawDownService(DrawDownParameters parameters) {
        this.parameters = parameters;
        listOfDrawDowns = new ArrayList<AnnualDrawdown>();
    }

    public List<AnnualDrawdown> calculateListOfDrawDowns() {

        AnnualDrawdown annualDrawdown = new AnnualDrawdown();
        annualDrawdown.setOpeningBalance(parameters.getStartingBalance().getValue());
        annualDrawdown.setWithdrawal(parameters.getYearlyWithdrawals().getValue());
        annualDrawdown.setIncome(calculateEarnings(annualDrawdown.getOpeningBalance()));
        annualDrawdown.setWithdrawal(calculateExpenses(annualDrawdown.getWithdrawal()));
        annualDrawdown.setClosingBalance(calculateClosingBalance(annualDrawdown.getOpeningBalance(), annualDrawdown.getIncome(), annualDrawdown.getWithdrawal()));
        annualDrawdown.setYear(1);
        listOfDrawDowns.add(annualDrawdown);
        Double previousBalance = annualDrawdown.getClosingBalance();
        Double previousWithdrawal = annualDrawdown.getWithdrawal();
        Double previousIncome = annualDrawdown.getIncome();
        Integer previousYear = annualDrawdown.getYear();



         while (annualDrawdown.getClosingBalance().doubleValue() > 0) {
             annualDrawdown = new AnnualDrawdown();
             annualDrawdown.setOpeningBalance(previousBalance);
            // annualDrawdown.setWithdrawal(annualDrawdown.getWithdrawal());
             annualDrawdown.setIncome(calculateEarnings(previousBalance));
             annualDrawdown.setWithdrawal(calculateExpenses(previousWithdrawal));
             annualDrawdown.setClosingBalance(calculateClosingBalance(previousBalance, annualDrawdown.getIncome(), annualDrawdown.getWithdrawal()));
             annualDrawdown.setYear(previousYear + 1);
             listOfDrawDowns.add(annualDrawdown);
              previousBalance = annualDrawdown.getClosingBalance();
              previousWithdrawal = annualDrawdown.getWithdrawal();
              previousIncome = annualDrawdown.getIncome();
              previousYear = annualDrawdown.getYear();
         }

        return listOfDrawDowns;




    }

    public List<AnnualDrawdown> getListOfDrawDowns() {
        return listOfDrawDowns;
    }

    public Integer getNumberOfYears() {
        return listOfDrawDowns.size();
    }

    private AnnualDrawdown createCopy(AnnualDrawdown annualDrawdown) {
        AnnualDrawdown copy = new AnnualDrawdown();
        copy.setOpeningBalance(annualDrawdown.getClosingBalance());
       // copy.setWithdrawal(annualDrawdown.getWithdrawal());
       // copy.setIncome(annualDrawdown.getIncome());

        copy.setYear(annualDrawdown.getYear());
        return copy;
    }


    private Double roundDouble(Double value) {
        return Double.valueOf(df.format(value));
    }
    private Double calculateEarnings(Double previousBalance) {
        return roundDouble(previousBalance * parameters.getPercentageReturnValue());
    }

    private Double calculateExpenses(Double annualExpenses) {
       // System.out.println("annual Expenses " + annualExpenses);
        Double b = (annualExpenses * parameters.getInflationRateValue()) + annualExpenses;
        return roundDouble(b);
    }

    private Double calculateClosingBalance(Double previousBalance, Double earnings, Double expenses) {
     //   System.out.println("previous balance " + previousBalance);
     //  System.out.println("earnings " + earnings);
     //   System.out.println("expenses " + expenses);
        Double b = (previousBalance + earnings) - expenses;
        if (b < 0) {
            b = 0.0;
        }
        return roundDouble(b);

    }

}
