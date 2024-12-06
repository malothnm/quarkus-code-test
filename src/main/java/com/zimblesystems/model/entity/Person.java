package com.zimblesystems.model.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Uni;

import java.util.Optional;

@MongoEntity(database = "test", collection = "person")
public class Person extends ReactivePanacheMongoEntity {

    private String name;

    private Integer age;

    private String gender;

    private Name nameGroup;




    public Person() {
    }

    public Person(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }



    public static Uni<Optional<Person>> findByName(String name){
        return find("name",name).firstResultOptional();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Name getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(Name nameGroup) {
        this.nameGroup = nameGroup;
    }
}
