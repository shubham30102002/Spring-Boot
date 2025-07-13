package com.example.springCoreDemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements  Coach{

    @PostConstruct
    public void breinitialization(){
        System.out.println("Bean "+getClass().getSimpleName() + " has been created");
    }

    @PreDestroy
    public void beforeDestory() {
        System.out.println("Bean "+ getClass().getSimpleName() + " has been destoried");
    }

    @Override
    public String getDailyWorkout(){
        return "Practice for 2hours";
    }
}
