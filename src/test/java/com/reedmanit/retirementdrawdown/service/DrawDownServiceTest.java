package com.reedmanit.retirementdrawdown.service;

import com.reedmanit.retirementdrawdown.model.DrawDownParameters;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import org.checkerframework.checker.units.qual.N;
import com.reedmanit.retirementdrawdown.model.AnnualDrawdown;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DrawDownServiceTest {

    public DrawDownServiceTest() {
    }

    @BeforeAll
    public static void setUpClass() {


    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetListOfDrawns() {
        System.out.println("getListOfDrawns");

        DrawDownParameters drawDownParameters = new DrawDownParameters();

        NumberField openBalanceField = new NumberField();
        openBalanceField.setValue(800000.0);
        NumberField earningsRate = new NumberField();
        earningsRate.setValue(0.05);
        NumberField annualWithdrawal= new NumberField();
        annualWithdrawal.setValue(70000.0);
        NumberField inflationRate = new NumberField();
        inflationRate.setValue(0.03);
        drawDownParameters.setStartingBalance(openBalanceField);
        drawDownParameters.setYearlyWithdrawals(annualWithdrawal);
        drawDownParameters.setInflationRate(inflationRate);
        drawDownParameters.setPercentageReturn(earningsRate);

        DrawDownService service = new DrawDownService(drawDownParameters);

        List<AnnualDrawdown> listOfDrawDowns = service.getListOfDrawDowns();

        System.out.println("Year " + "Open Balance " + "Withdrawal " + "Income " + "Close Balance ");
        for (AnnualDrawdown annualDrawdown : listOfDrawDowns) {
            System.out.println(annualDrawdown.getYear() + " " + annualDrawdown.getOpeningBalance() + " " + annualDrawdown.getWithdrawal() + " " + annualDrawdown.getIncome() + " " + annualDrawdown.getClosingBalance());
            
        }



    }


}
