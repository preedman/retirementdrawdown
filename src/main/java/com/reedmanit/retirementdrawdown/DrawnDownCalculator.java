/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.reedmanit.retirementdrawdown;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Push(PushMode.MANUAL)
@Theme(value = "drawdowncalculator")
//@PWA(name = "Retirement Drawdown App",
//        shortName = "RDA")
public class DrawnDownCalculator implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(DrawnDownCalculator.class, args);
    }

}

