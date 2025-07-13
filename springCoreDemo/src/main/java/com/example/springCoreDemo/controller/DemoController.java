package com.example.springCoreDemo.controller;

import com.example.springCoreDemo.common.Coach;
import com.example.springCoreDemo.common.CricketCoach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private Coach myCoach;

    @Autowired
    CricketCoach cricketCoach;

    @Autowired
    public DemoController(@Qualifier("swimCoach") Coach theCoach){
        System.out.println("In constructor "+ getClass().getSimpleName());
        //System.out.println("Cricket coach obj in DemoController "+ cricketCoach.getDailyWorkout());
        myCoach = theCoach;
    }

    @GetMapping("/dailyWorkout")
    public String dailyWorkout() {
        cricketCoachCall();
        return myCoach.getDailyWorkout();
    }

    protected void cricketCoachCall(){
        System.out.println(cricketCoach.getDailyWorkout());
    }
}
