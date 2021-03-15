package com.valyo95.microservices.cardcostservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * A simple Spring security configuration
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint authEntryPoint;

    private final boolean csrfEnabled;

    public SecurityConfig(AuthenticationEntryPoint authEntryPoint, @Value("${security.enable-csrf}") boolean csrfEnabled) {
        this.authEntryPoint = authEntryPoint;
        this.csrfEnabled = csrfEnabled;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{noop}user")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // needed in order to show the h2-console
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                // only an user with the ADMIN role can change the default clearing cost
                .antMatchers(HttpMethod.POST, "/admin/defaultClearingCost").access("hasRole('ADMIN')")
                .anyRequest()
                .authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(authEntryPoint);
    }
}