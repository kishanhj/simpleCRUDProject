package com.perficient.simplecrudproject.services;

import com.perficient.simplecrudproject.exceptions.NoSuchPersonException;
import com.perficient.simplecrudproject.model.Person;
import com.perficient.simplecrudproject.repositories.AddressRepository;
import com.perficient.simplecrudproject.repositories.PersonRepository;
import com.perficient.simplecrudproject.serviceImpls.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonServiceImplTest {

    PersonService personService;

    @Mock
    PersonRepository personRepository;

    @Mock
    AddressRepository addressRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        personService = new PersonServiceImpl(personRepository,addressRepository);
    }

    @Test
    void getPersonByIdTestNormalFlow() {
        try {
            Person kishan = new Person("Kishan","Huliyar");
            kishan.setId(1l);
            when(personRepository.findById(1l)).thenReturn(Optional.of(kishan));
            Person p = personService.getPersonById(1l);
            assertEquals(kishan.getFirstName(),p.getFirstName());
        } catch (Exception e){

        }
    }

    @Test
    void getPersonByIdTestNotFound(){
        when(personRepository.findById(1l)).thenReturn(Optional.empty());
        assertThrows(NoSuchPersonException.class, () -> personService.getPersonById(1l));
    }

    @Test
    void getPersonByIdTestBadInput(){
        assertThrows(IllegalArgumentException.class, () -> personService.getPersonById(null));
    }

    @Test
    void getAllPeopleTestNormalFlow() throws ExecutionException, InterruptedException {
        ArrayList<Person> expected = new ArrayList<>(){{
            add(new Person("abc","def"));
            add(new Person("def","hij"));
            add(new Person("asd","fgh"));
        }};
        when(personRepository.findAll()).thenReturn(expected);

        List<Person> actual = personService.getAllPeople();
        Assertions.assertIterableEquals(expected,actual);
    }

    @Test
    void addPersonTestBadInput() {
        assertThrows(IllegalArgumentException.class, () -> personService.addPerson(null));
    }

    @Test
    void addPersonTestNormalFlow() throws ExecutionException, InterruptedException {
        Person expected = new Person("Kishan","Huliyar");
        expected.setId(1l);
        when(personRepository.save(expected)).thenReturn(expected);
        Person actual = personService.addPerson(expected);
        assertEquals(expected.getFirstName(),actual.getFirstName());
    }

    @Test
    void updatePersonTestBadInput() {
        assertThrows(IllegalArgumentException.class, () -> personService.updatePerson(null));
    }

    @Test
    void updatePersonTestNotFound(){
        Person p = new Person("abc","def");
        when(personRepository.existsById(p.getId())).thenReturn(false);
        assertThrows(NoSuchPersonException.class, () -> personService.updatePerson(p));
    }

    @Test
    void deletePersonTestBadInput() {
        assertThrows(IllegalArgumentException.class, () -> personService.deletePerson(null));
    }

    @Test
    void deletePersonTestNotFound(){
        Person p = new Person("abc","def");
        when(personRepository.existsById(p.getId())).thenReturn(false);
        assertThrows(NoSuchPersonException.class, () -> personService.deletePerson(p));
    }

}