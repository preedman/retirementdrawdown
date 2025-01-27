package com.reedmanit.retirementdrawdown.service;

import com.reedmanit.retirementdrawdown.security.LoginView;
import org.springframework.context.annotation.Configuration;
import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(
                        AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.png")).permitAll());  // <3>
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService users() {
        UserDetails guest = User.builder()
                .username("guest")
                .password("{bcrypt}$2a$10$y2AMrMYHBb9pWmiI1kp7aemMffZMavrCBDMWhlxYLoMnyv0UNB5J.")
                .roles("USER")
                .build();


        return new InMemoryUserDetailsManager(guest);
    }
}
