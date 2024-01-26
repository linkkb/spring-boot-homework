package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //Field injection is out of favor because it makes unit testing difficult
    //@Autowired
    private Coach myCoach;
    private Coach anotherCoach;

    //Constructor Injection
    @Autowired
    // Specifies bean to use
    public void DemoController(@Qualifier("swimCoach") Coach theCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        this.myCoach = theCoach;
        this.anotherCoach = theCoach;
    }
//*/

 /*   // Uses default bean marked by @Primary
    public void DemoController(Coach theCoach, Coach otherCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        this.myCoach = theCoach;
        this.anotherCoach = otherCoach;
    }
//*/

/*    // Setter Injection
    @Autowired
    public void setCoach(@Qualifier("trackCoach") Coach theCoach) {
        System.out.println("In constructor: " + getClass().getSimpleName());
        this.myCoach = theCoach;
    }
 //*/

/*
    @Autowired
    public DemoController(Coach theCoach) {
        myCoach = theCoach;
    }
//*/

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check() {
        return "Is same coach: " + (myCoach == anotherCoach);
    }

}


