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


    private final ArrayList<AnnualDrawdown> listOfTestAnnualDrawdown = new ArrayList<>();

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

        AnnualDrawdown annualDrawdown1 = new AnnualDrawdown();
        annualDrawdown1.setYear(1);
        annualDrawdown1.setOpeningBalance(800000.0);
        annualDrawdown1.setWithdrawal(72100.0);
        annualDrawdown1.setIncome(40000.0);
        annualDrawdown1.setClosingBalance(767900.0);
        listOfTestAnnualDrawdown.add(annualDrawdown1);

        AnnualDrawdown annualDrawdown2 = new AnnualDrawdown();
        annualDrawdown2.setYear(2);
        annualDrawdown2.setOpeningBalance(767900.0);
        annualDrawdown2.setWithdrawal(74263.0);
        annualDrawdown2.setIncome(38395.0);
        annualDrawdown2.setClosingBalance(732032.0);
        listOfTestAnnualDrawdown.add(annualDrawdown2);

        AnnualDrawdown annualDrawdown3 = new AnnualDrawdown();
        annualDrawdown3.setYear(3);
        annualDrawdown3.setOpeningBalance(732032.0);
        annualDrawdown3.setWithdrawal(76490.89);
        annualDrawdown3.setIncome(36601.6);
        annualDrawdown3.setClosingBalance(692142.71);
        listOfTestAnnualDrawdown.add(annualDrawdown3);
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
        earningsRate.setValue(5.0);
        NumberField annualWithdrawal= new NumberField();
        annualWithdrawal.setValue(70000.0);
        NumberField inflationRate = new NumberField();
        inflationRate.setValue(3.0);
        drawDownParameters.setStartingBalance(openBalanceField);
        drawDownParameters.setYearlyWithdrawals(annualWithdrawal);
        drawDownParameters.setInflationRate(inflationRate);
        drawDownParameters.setPercentageReturn(earningsRate);

        DrawDownService service = new DrawDownService(drawDownParameters);

        List<AnnualDrawdown> listOfDrawDowns = service.getListOfDrawDowns();

        assertEquals(14, listOfDrawDowns.size());
        assertEquals(listOfTestAnnualDrawdown.get(0), listOfDrawDowns.get(0));
        assertEquals(listOfTestAnnualDrawdown.get(1), listOfDrawDowns.get(1));
        assertEquals(listOfTestAnnualDrawdown.get(2), listOfDrawDowns.get(2));

        System.out.println("Year " + "Open Balance " + "Withdrawal " + "Income " + "Close Balance ");
        for (AnnualDrawdown annualDrawdown : listOfDrawDowns) {
            System.out.println(annualDrawdown.getYear() + " " + annualDrawdown.getOpeningBalance() + " " + annualDrawdown.getWithdrawal() + " " + annualDrawdown.getIncome() + " " + annualDrawdown.getClosingBalance());


        }



    }


}
