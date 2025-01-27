package com.reedmanit.retirementdrawdown.views;

import com.reedmanit.retirementdrawdown.model.DrawDownParameters;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;


public class ParametersDialogView extends Dialog {

    private DrawDownParameters parameters;

    public ParametersDialogView(DrawDownParameters theParameters)
    {
        parameters = theParameters;
        createDialog();

    }

    public void createDialog() {
        TextField sb = new TextField("Starting Balance");
        sb.setValue(parameters.getStartingBalanceAsString());
        sb.setReadOnly(true);

        TextField rateOfReturn = new TextField("Rate of Return");
        rateOfReturn.setReadOnly(true);
        rateOfReturn.setValue(parameters.getPercentageReturnAsString());

        TextField inflationRate = new TextField("Inflation Rate");
        inflationRate.setReadOnly(true);
        inflationRate.setValue(parameters.getInflationRateAsString());

        TextField withdrawal = new TextField("Withdrawal");
        withdrawal.setValue(parameters.getYearlyWithdrawalsAsString());
        withdrawal.setReadOnly(true);

        VerticalLayout fieldLayout = new VerticalLayout(sb, withdrawal, inflationRate, rateOfReturn);
        fieldLayout.setSpacing(false);
        fieldLayout.setPadding(false);
        fieldLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        fieldLayout.getStyle().set("width", "300px").set("max-width", "100%");

        add(fieldLayout);
        setHeaderTitle("Drawdown Parameters");
        Button closeButton = new Button(new Icon("lumo", "cross"),
                (e) -> this.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        getHeader().add(closeButton);

    }
}
