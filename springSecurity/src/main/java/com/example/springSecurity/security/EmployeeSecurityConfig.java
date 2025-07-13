package com.example.springSecurity.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class EmployeeSecurityConfig {

    //fetching user form db using jdbc
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from auth_users where username=?");

        // define query to retrieve the authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, authority from auth_authorities where username=?");

        return jdbcUserDetailsManager;

        //when you are using spring configured table only one line of code (users, authorities) --> default
        //return new JdbcUserDetailsManager(dataSource);
    }

  /*  @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("johnny")
                .password("{noop}1234")
                .roles("EMPLOYEE")
                .build();

        UserDetails shubham = User.builder()
                .username("shubham")
                .password("{noop}pass123")
                .roles("ADMIN", "MANAGER","EMPLOYEE")
                .build();

        UserDetails yash = User.builder()
                .username("yash")
                .password("{noop}yash123")
                .roles("MANAGER", "EMPLOYEE")
                .build();

        return new InMemoryUserDetailsManager(john, shubham, yash);

    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception{
        httpSec.authorizeHttpRequests(configure ->
                configure.requestMatchers(HttpMethod.GET, "/api/employee").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employee/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employee").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employee").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PATCH, "/api/employee/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employee/**").hasRole("ADMIN")
        );

        //use HTTP basic auth
        httpSec.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        httpSec.csrf(csrf -> csrf.disable());

        return httpSec.build();
    }
}
