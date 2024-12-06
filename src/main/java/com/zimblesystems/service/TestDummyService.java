package com.zimblesystems.service;

import io.quarkus.logging.Log;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestDummyService {

    @PostConstruct
    public void postConstruct(){
        Log.info(" initialized " );
    }

    public  String createName(String name){
        return "Mr " + name;
    }
}
