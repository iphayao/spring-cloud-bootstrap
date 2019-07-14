package com.iphayao.ratingservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable()
                .authorizeRequests()
                .regexMatchers("^/ratings\\?bookId.*$").authenticated()
                .antMatchers(HttpMethod.POST, "/ratings").authenticated()
                .antMatchers(HttpMethod.PATCH, "/ratings/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/ratings/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/ratings").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
    }
}
