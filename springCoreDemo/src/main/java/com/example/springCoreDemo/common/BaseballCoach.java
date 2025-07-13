package com.example.springCoreDemo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{
    @Override
    public String getDailyWorkout(){
        return "Practice batting for 1 hour";
    }
}
