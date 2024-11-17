package com.reedmanit.retirementdrawdown.views;

import com.reedmanit.retirementdrawdown.model.AnnualDrawdown;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.List;

public class DrawdownGridView {

    private Grid<AnnualDrawdown> theGrid;

    public DrawdownGridView() {
        theGrid = new Grid<AnnualDrawdown>();
        drawGrid();
       // this.add(getTheGrid());
    }

    private void drawGrid() {
       // getTheGrid().setSizeFull();
        getTheGrid().setSelectionMode(Grid.SelectionMode.SINGLE);
        getTheGrid().addColumn(AnnualDrawdown::getYear).setHeader("Year").setAutoWidth(true);
        getTheGrid().addColumn(AnnualDrawdown::getOpeningBalance).setHeader("Opening balance").setAutoWidth(true);
        getTheGrid().addColumn(AnnualDrawdown::getWithdrawal).setHeader("Withdrawal").setAutoWidth(true);
        getTheGrid().addColumn(AnnualDrawdown::getIncome).setHeader("Income").setAutoWidth(true);
        getTheGrid().addColumn(AnnualDrawdown::getClosingBalance).setHeader("Closing balance").setAutoWidth(true);

    }

    public Grid<AnnualDrawdown> getTheGrid() {
        return theGrid;
    }

    public void setTheGrid(List<AnnualDrawdown> theGrid) {
        this.theGrid.setItems(theGrid);
    }
}
