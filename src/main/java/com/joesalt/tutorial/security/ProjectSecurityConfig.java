package com.joesalt.tutorial.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.RequestMatcherDelegatingAuthorizationManager;
import org.springframework.security.web.util.matcher.RequestMatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ALLOWED_HOST = "devtron.ai";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
            .antMatchers("/notices", "/contact").permitAll()
            .anyRequest().denyAll()
            .and().formLogin()
            .and().httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/images/**");
    }

    private boolean isFromAllowedHost(HttpServletRequest request) {
        return ALLOWED_HOST.equals(request.getRemoteHost());
    }
}
