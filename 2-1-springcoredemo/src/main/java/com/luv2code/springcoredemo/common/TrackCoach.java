package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary //Indicates the default bean
//@Lazy //Initialize only when needed
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) //one instance shared by all
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) //new instance for each call
//Prototype beans are lazy by default
//They also do not execute @PreDestroy code
//@Scope(ConfigurableBeanFactory.SCOPE_REQUEST) //One instance per web request
//@Scope(ConfigurableBeanFactory.SCOPE_SESSION) //One instance per web session
//@Scope(ConfigurableBeanFactory.SCOPE_GLOBAL_SESSION) //One instance per global web session
public class TrackCoach implements Coach {

    public TrackCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("In doMyStartupStuff(): " + getClass().getSimpleName());
    }
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("In doMyCleanupStuff(): " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Go running :)";
    }


}
