package com.zimblesystems.service;

import com.zimblesystems.model.dto.PersonDTO;
import com.zimblesystems.model.entity.Person;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@ApplicationScoped
public class GreetingsServiceImpl implements GreetingsService {


    @ConfigProperty(name = "hello")
    String greeting;

    @Override
    public String createGreetings(String name) {
        return greeting + " How do you do " + name;
    }

    @Override
    public PersonDTO convertToDTO(Person person) {

        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName());
        personDTO.setGender(person.getGender());
        personDTO.setAge(person.getAge());

        return personDTO;
    }

    @Override
    public Person convertFromDTO(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setGender(personDTO.getGender());
        person.setAge(personDTO.getAge());
        return person;


    }

    @Override
    public Uni<Optional<Person>> fetchPerson(String name) {

        return Person.findByName(name);
    }

    @Override
    public Uni<Void> addPerson(PersonDTO personDTO) {
        Person person = convertFromDTO(personDTO);
        return Person.persist(person)
                ;
    }

    @Override
    public Uni<Optional<PersonDTO>> getPerson(String name) {
        return Person.findByName(name).map(person -> person.map(this::convertToDTO));

    }
}
