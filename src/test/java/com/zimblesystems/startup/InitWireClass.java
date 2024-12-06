package com.zimblesystems.startup;

import com.zimblesystems.model.entity.Person;
import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class InitWireClass implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {

        Log.info(" initialized  #3###################################");
//        Person person = new Person();
//        person.setName("John");
//        person.setAge(25);
//        person.setGender("Male");
//        person.persist().await().indefinitely();
        return Map.of();
    }

    @Override
    public void stop() {

    }
}
