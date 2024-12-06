package com.zimblesystems.resource;

import com.zimblesystems.model.dto.PersonDTO;
import com.zimblesystems.model.entity.Person;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/hello/nithin")
                .then()
                .statusCode(200)
                .body(is("hello there How do you do nithin"));
    }


    @Test
    void addPerson() {

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("Johny");
        personDTO.setAge(36);
        personDTO.setGender("Male");

        given()
                .when().contentType("application/json")
                .accept("application/json")
                .body(personDTO).post("/hello/add")
                .then()
                .statusCode(201)
        ;
    }

    @Test
    void viewPerson() {


        Person person = new Person();
        person.setName("Jane");
        person.setAge(45);
        person.setGender("Female");

        person.persist().await().indefinitely();

        given()
                .when().get("/hello/view/Jane")
                .then()
                .statusCode(200)
                .body("name", is("Jane"))
                .body("age", is(45))
                .body("gender", is("Female"))

        ;
    }
}