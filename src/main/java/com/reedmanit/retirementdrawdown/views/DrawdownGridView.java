package com.reedmanit.retirementdrawdown.views;

import com.reedmanit.retirementdrawdown.model.AnnualDrawdown;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.NumberRenderer;

import java.text.NumberFormat;
import java.util.List;
import java.util.stream.Stream;

public class DrawdownGridView {

    private Grid<AnnualDrawdown> theGrid;

    public DrawdownGridView() {
        theGrid = new Grid<AnnualDrawdown>();
        drawGrid();
       // this.add(getTheGrid());
    }

    private void drawGrid() {

        getTheGrid().setSelectionMode(Grid.SelectionMode.SINGLE);
        getTheGrid().addColumn(AnnualDrawdown::getYear).setHeader("Year").setAutoWidth(true).setSortable(true);
        getTheGrid().addColumn(new NumberRenderer<>(AnnualDrawdown::getOpeningBalance, NumberFormat.getCurrencyInstance())).setHeader("Opening balance").setAutoWidth(true);

        getTheGrid().addColumn(new NumberRenderer<>(AnnualDrawdown::getWithdrawal, NumberFormat.getCurrencyInstance())).setHeader("Withdrawal").setAutoWidth(true);
        getTheGrid().addColumn(new NumberRenderer<>(AnnualDrawdown::getIncome, NumberFormat.getCurrencyInstance())).setHeader("Income").setAutoWidth(true);
        getTheGrid().addColumn(new NumberRenderer<>(AnnualDrawdown::getClosingBalance, NumberFormat.getCurrencyInstance())).setHeader("Closing balance").setAutoWidth(true);
        getTheGrid().addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

    }

    public Grid<AnnualDrawdown> getTheGrid() {
        return theGrid;
    }

    public void setTheGrid(List<AnnualDrawdown> theGrid) {
        this.theGrid.setItems(theGrid);

    }
    public Stream<AnnualDrawdown> getGridStream() {
        return this.theGrid.getGenericDataView().getItems();
    }
}
