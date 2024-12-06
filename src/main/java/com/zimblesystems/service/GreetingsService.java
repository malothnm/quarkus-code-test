package com.zimblesystems.service;

import com.zimblesystems.model.dto.PersonDTO;
import com.zimblesystems.model.entity.Person;
import io.smallrye.mutiny.Uni;

import java.util.Optional;

public interface GreetingsService {

    String createGreetings(String name);

    PersonDTO convertToDTO(Person person);

    Person convertFromDTO(PersonDTO personDTO);

    Uni<Optional<Person>> fetchPerson(String name);

    Uni<Void> addPerson(PersonDTO personDTO);

    Uni<Optional<PersonDTO>> getPerson(String name);




}
