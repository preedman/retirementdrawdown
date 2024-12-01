package com.reedmanit.retirementdrawdown.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.formlayout.FormLayout;

public class AppGridView extends Composite<Component> {
    private ParameterFormView parameterFormView;
    private ParameterView parameterView;
    private FormLayout formLayout;
    public AppGridView() {

    }
    @Override
    protected Component initContent() {
     //   parameterFormView = new ParameterFormView();
      //  setUpFormParameters();
      //  return this;
        parameterView = new ParameterView();
        parameterView.setHeight("100%");
        return parameterView;
    }

    private void setUpFormParameters() {
        formLayout = new FormLayout();
        formLayout.add(parameterFormView.getStartBalanceNF(),
                        parameterFormView.getPercentageReturnNF(),
                        parameterFormView.getYearlyWithdrawalsNF(),
                        parameterFormView.getInflationRateNF());
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if layout's width exceeds 500px
                new FormLayout.ResponsiveStep("500px", 2));

    }
}
