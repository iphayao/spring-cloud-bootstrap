package com.iphayao.gatewayserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/book-service/books").permitAll()
                .antMatchers("/eureka/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin()
                .and().logout().permitAll()
                .and().csrf().disable();
    }
}
