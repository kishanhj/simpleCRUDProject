package com.perficient.simplecrudproject.services;

import com.perficient.simplecrudproject.exceptions.NoSuchPersonException;
import com.perficient.simplecrudproject.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public interface PersonService {

    public Person getPersonById(Long id) throws NoSuchPersonException, InterruptedException, ExecutionException;

    public List<Person> getAllPeople() throws ExecutionException, InterruptedException;

    public Person addPerson(Person p) throws ExecutionException, InterruptedException;

    public Person updatePerson(Person p) throws NoSuchPersonException, InterruptedException, ExecutionException;

    public Long deletePerson(Person p) throws NoSuchPersonException, InterruptedException, ExecutionException;
}
