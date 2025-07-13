package com.example.springSecurityThymleaf.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();

        //query to fetch the user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from auth_users where username=?");

        //query to fetch the role by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, authority from auth_authorities where username=?");

        return jdbcUserDetailsManager;
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//
//        UserDetails shubham = User.builder()
//                .username("shubham")
//                .password("{noop}pass123")
//                .roles("ADMIN","MANAGER","EMPLOYEE")
//                .build();
//
//        UserDetails yash = User.builder()
//                .username("yash")
//                .password("{noop}yash123")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails kuber = User.builder()
//                .username("kuber")
//                .password("{noop}pass123")
//                .roles("MANAGER","EMPLOYEE")
//                .build();
//
//        return new InMemoryUserDetailsManager(shubham, yash, kuber);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configure ->
                        configure
                                .requestMatchers("/").hasRole("EMPLOYEE")
                                .requestMatchers("/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/systems/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(form ->
                        form.loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configuer ->
                        configuer.accessDeniedPage("/access-denied"));

        return http.build();
    }
}
