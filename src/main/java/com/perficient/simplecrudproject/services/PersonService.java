package com.perficient.simplecrudproject.services;

import com.perficient.simplecrudproject.exceptions.NoSuchPersonException;
import com.perficient.simplecrudproject.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService {

    public Person getPersonById(Long id) throws NoSuchPersonException;

    public List<Person> getAllPeople();

    public Person addPerson(Person p);

    public Person updatePerson(Person p) throws NoSuchPersonException;

    public Long deletePerson(Person p) throws NoSuchPersonException;
}
