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

import com.reedmanit.retirementdrawdown.model.DrawDownParameters;
import com.reedmanit.retirementdrawdown.service.DrawDownService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 *
 * @author preed
 */
@Route
public class MainView extends VerticalLayout {
    
    private ParameterView parameterView;
    private CalculatorInformation calculatorInformation;
    private ButtonView buttonView;
    private DrawdownGridView gridView;
    private DrawDownService drawnDownService;
    private DrawDownParameters drawDownParameters;
    
    public MainView() {



        drawDownParameters = new DrawDownParameters();
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        calculatorInformation = new CalculatorInformation();

        buttonView = new ButtonView();

        parameterView = new ParameterView();
        parameterView.setSizeFull();
        this.add(calculatorInformation);
        this.add(parameterView);
        this.add(buttonView);
        gridView = new DrawdownGridView();
        this.add(gridView.getTheGrid());


        buttonView.getCalculateBTN().addClickListener(e -> {
            System.out.println("Button clicked");
            drawDownParameters.setStartingBalance(parameterView.getStartBalanceNF());
            drawDownParameters.setYearlyWithdrawals(parameterView.getYearlyWithdrawalsNF());
            drawDownParameters.setPercentageReturn(parameterView.getPercentageReturnNF());
            drawDownParameters.setInflationRate(parameterView.getInflationRateNF());
            drawnDownService = new DrawDownService(drawDownParameters);
            gridView.getTheGrid().setItems(drawnDownService.getListOfDrawDowns());

        });


        
    }
    
    
}
