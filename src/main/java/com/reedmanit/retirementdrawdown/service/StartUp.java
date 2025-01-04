package com.reedmanit.retirementdrawdown.service;

import com.reedmanit.retirementdrawdown.model.Role;
import com.reedmanit.retirementdrawdown.model.User;
import com.reedmanit.retirementdrawdown.model.UserRepository;
import com.reedmanit.retirementdrawdown.model.Version;
import com.vaadin.flow.server.ServiceInitEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class StartUp {


    private User user;
    private Version version;


    @EventListener
    public void logSessionInits(ServiceInitEvent event) {
        event.getSource().addSessionInitListener(
                sessionInitEvent -> {
                    System.out.println("Start Up");
                    try {
                        retrieveConfiguration();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });


    }

    private void retrieveConfiguration() throws IOException {


        UserRepository.setUp();

        Resource resource = new ClassPathResource("/application.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        String userName=props.getProperty("userPaul");
        String password=props.getProperty("passwordPaul");

        user = new User(userName, password, Role.USER );
        UserRepository.addUser(user);

        userName = props.getProperty("userGuest");
        password=props.getProperty("passwordGuest");

        user = new User(userName, password, Role.USER );
        UserRepository.addUser(user);

        userName = props.getProperty("userJenny");
        password=props.getProperty("passwordJenny");

        user = new User(userName, password, Role.USER );
        UserRepository.addUser(user);

        String versionString = props.getProperty("version");
        String dateString = props.getProperty("date");
        version = new Version(versionString, dateString);




    }
}
