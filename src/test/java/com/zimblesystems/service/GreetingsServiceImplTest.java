package com.zimblesystems.service;

import com.zimblesystems.model.dto.PersonDTO;
import com.zimblesystems.model.entity.Person;
import com.zimblesystems.startup.InitWireClass;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(InitWireClass.class)
class GreetingsServiceImplTest {


    @Inject
    GreetingsService greetingsService;

    @Test
    void createGreetings() {
        String name = "John";
        String greetings = greetingsService.createGreetings(name);
        assertEquals("hello there How do you do John", greetings);

    }

    @Test
    void convertToDTO() {

        Person person = new Person();
        person.setName("John");
        person.setAge(25);
        person.setGender("Male");

        PersonDTO personDTO = greetingsService.convertToDTO(person);

        assertAll(
                () -> assertEquals(person.getName(), personDTO.getName()),
                () -> assertEquals(person.getAge(), personDTO.getAge()),
                () -> assertEquals(person.getGender(), personDTO.getGender())
        );


    }

    @Test
    void convertFromDTO() {

            PersonDTO personDTO = new PersonDTO();
            personDTO.setName("John");
            personDTO.setAge(25);
            personDTO.setGender("Male");
            assertAll(
                    () -> assertEquals(personDTO.getName(), greetingsService.convertFromDTO(personDTO).getName()),
                    () -> assertEquals(personDTO.getAge(), greetingsService.convertFromDTO(personDTO).getAge()),
                    () -> assertEquals(personDTO.getGender(), greetingsService.convertFromDTO(personDTO).getGender())
            );
    }


    @Test
    void viewFromDatabase() {

     Person person = new Person();
        person.setName("John");
        person.setAge(25);
        person.setGender("Male");
        person.persist().await().indefinitely();

        Uni<Optional<Person>> personUni = greetingsService.fetchPerson("John");
        Optional<Person> personOptional = personUni.await().indefinitely();

        assertAll(
                () -> assertTrue(personOptional.isPresent()),
                () -> assertEquals(person.getName(), personOptional.get().getName()),
                () -> assertEquals(person.getAge(), personOptional.get().getAge()),
                () -> assertEquals(person.getGender(), personOptional.get().getGender())

        );
    }

    @Test
    void addPerson() {

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("RitaAdd");
        personDTO.setAge(27);
        personDTO.setGender("Female");

        greetingsService.addPerson(personDTO).await().indefinitely();


        Person.findByName("RitaAdd").await().indefinitely().ifPresentOrElse(
                person -> assertAll(
                        () -> assertEquals(personDTO.getName(), person.getName()),
                        () -> assertEquals(personDTO.getAge(), person.getAge()),
                        () -> assertEquals(personDTO.getGender(), person.getGender())
                ),
                () -> fail("Person not found")
        );

    }

    @Test
    void getPerson() {

        Person person = new Person();
        person.setName("John_View");
        person.setAge(28);
        person.setGender("Male");

        person.persist().await().indefinitely();

        Uni<Optional<PersonDTO>> personDTOUni = greetingsService.getPerson("John_View");

        personDTOUni.await().indefinitely().ifPresentOrElse(
                personDTO -> assertAll(
                        () -> assertEquals(person.getName(), personDTO.getName()),
                        () -> assertEquals(person.getAge(), personDTO.getAge()),
                        () -> assertEquals(person.getGender(), personDTO.getGender())
                ),
                () -> fail("Person not found")
        );
    }
}