package com.example.springCoreDemo.common;

public class SwimCoach implements Coach{

    public void SwimCoach(){
        System.out.println("In constructor "+ getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Swim daily for 3 hours";
    }
}
