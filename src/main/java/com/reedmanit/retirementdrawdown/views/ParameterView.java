/*
 * Copyright 2024 preed.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.reedmanit.retirementdrawdown.views;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.NumberField;

/**
 *
 * @author preed
 */
public class ParameterView extends HorizontalLayout {
    
    private NumberField startBalanceNF;  // start balance
    
    private NumberField percentageReturnNF;  // % returns
    
    private NumberField yearlyWithdrawalsNF; // how much withdraw
    
    private NumberField inflationRateNF; // % inflation rate
    
    public ParameterView() {
        
        setUpParameterView();
        
        this.getStyle().set("border", "1px solid");
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        
    }
    
    private void setUpParameterView() {
        
        setStartBalanceNF(new NumberField());
        getStartBalanceNF().setRequired(true);
        getStartBalanceNF().setMin(10000.00);
        getStartBalanceNF().setMax(2000000.00);
        getStartBalanceNF().setLabel("Starting Balance");
        getStartBalanceNF().setErrorMessage("Invalid starting Balance");
        getStartBalanceNF().setRequiredIndicatorVisible(true);
        getStartBalanceNF().setClearButtonVisible(true);

        getStartBalanceNF().setPrefixComponent(new Span("$"));
        this.add(getStartBalanceNF());
        
        setPercentageReturnNF(new NumberField());
        getPercentageReturnNF().setRequired(true);
        getPercentageReturnNF().setMin(1.00);
        getPercentageReturnNF().setMax(20.00);
        getPercentageReturnNF().setRequiredIndicatorVisible(true);
        getPercentageReturnNF().setErrorMessage("Invalid percentage Return");
        getPercentageReturnNF().setLabel("Annual % return");
        getPercentageReturnNF().setClearButtonVisible(true);

        getPercentageReturnNF().setSuffixComponent(new Span("%"));
        this.add(getPercentageReturnNF());
        
        setYearlyWithdrawalsNF(new NumberField());
        getYearlyWithdrawalsNF().setRequired(true);
        getYearlyWithdrawalsNF().setLabel("Annual withdrawal");
        getYearlyWithdrawalsNF().setMin(500.00);
        getYearlyWithdrawalsNF().setMax(1000000.00);
        getYearlyWithdrawalsNF().setRequiredIndicatorVisible(true);
        getYearlyWithdrawalsNF().setErrorMessage("Invalid yearly Withdrawal");
        getYearlyWithdrawalsNF().setClearButtonVisible(true);
        getYearlyWithdrawalsNF().setPrefixComponent(new Span("$"));
        this.add(getYearlyWithdrawalsNF());
        
        setInflationRateNF(new NumberField());
        getInflationRateNF().setRequired(true);
        getInflationRateNF().setLabel("Annual % inflation rate");
        getInflationRateNF().setMin(1.00);
        getInflationRateNF().setMax(20.00);
        getInflationRateNF().setRequiredIndicatorVisible(true);
        getInflationRateNF().setErrorMessage("Invalid inflation Rate");
        getInflationRateNF().setClearButtonVisible(true);
        getInflationRateNF().setSuffixComponent(new Span("%"));
        this.add(getInflationRateNF());
        
        
        
    }

    public NumberField getStartBalanceNF() {
        return startBalanceNF;
    }

    public void setStartBalanceNF(NumberField startBalanceNF) {
        this.startBalanceNF = startBalanceNF;
    }

    public NumberField getPercentageReturnNF() {
        return percentageReturnNF;
    }

    public void setPercentageReturnNF(NumberField percentageReturnNF) {
        this.percentageReturnNF = percentageReturnNF;
    }

    public NumberField getYearlyWithdrawalsNF() {
        return yearlyWithdrawalsNF;
    }

    public void setYearlyWithdrawalsNF(NumberField yearlyWithdrawalsNF) {
        this.yearlyWithdrawalsNF = yearlyWithdrawalsNF;
    }

    public NumberField getInflationRateNF() {
        return inflationRateNF;
    }

    public void setInflationRateNF(NumberField inflationRateNF) {
        this.inflationRateNF = inflationRateNF;
    }
}
