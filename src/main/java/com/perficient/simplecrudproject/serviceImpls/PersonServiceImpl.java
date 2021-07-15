package com.perficient.simplecrudproject.serviceImpls;

import com.perficient.simplecrudproject.exceptions.NoSuchPersonException;
import com.perficient.simplecrudproject.model.Person;
import com.perficient.simplecrudproject.repositories.AddressRepository;
import com.perficient.simplecrudproject.repositories.PersonRepository;
import com.perficient.simplecrudproject.services.PersonService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Profile("seq")
@Service
public class PersonServiceImpl implements PersonService {
    public PersonRepository personRepository;
    public AddressRepository addressRepository;

    public PersonServiceImpl(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Person getPersonById(Long id) throws IllegalArgumentException, NoSuchPersonException {
        Assert.notNull(id, "ID cannot be null");
        Person p = personRepository.findById(id).orElse(null);

        if(null == p){
            throw new NoSuchPersonException("Person not Found");
        }
        p.setAddresses(addressRepository.getAddressesById(1l));
        return p;
    }

    @Override
    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        personRepository.findAll().forEach(person -> {
            person.setAddresses(addressRepository.getAddressesById(1l));
            people.add(person);
        });
        return people;
    }

    @Override
    public Person addPerson(Person p) {
        Assert.notNull(p, "Person cannot be null");
        Person savedPerson = personRepository.save(p);
        savedPerson.setAddresses(addressRepository.getAddressesById(1l));
        return savedPerson;
    }

    @Override
    public Person updatePerson(Person p) throws IllegalArgumentException, NoSuchPersonException {
        Assert.notNull(p, "Person cannot be null");
        if(!personRepository.existsById(p.getId()))
            throw new NoSuchPersonException("Person not Found");
        Person updatedPerson = personRepository.save(p);
        updatedPerson.setAddresses(addressRepository.getAddressesById(1l));
        return updatedPerson;
    }

    @Override
    public Long deletePerson(Person p) throws  IllegalArgumentException, NoSuchPersonException{
        Assert.notNull(p, "Person cannot be null");
        if(!personRepository.existsById(p.getId()))
            throw new NoSuchPersonException("Person not Found");
        personRepository.delete(p);
        return p.getId();
    }
}
