package com.perficient.simplecrudproject.serviceImpls;

import com.perficient.simplecrudproject.exceptions.NoSuchPersonException;
import com.perficient.simplecrudproject.model.Address;
import com.perficient.simplecrudproject.model.Person;
import com.perficient.simplecrudproject.repositories.AddressRepository;
import com.perficient.simplecrudproject.repositories.PersonRepository;
import com.perficient.simplecrudproject.services.PersonService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Profile("async")
@Service
public class PersonServiceImplAsync implements PersonService {

    public PersonRepository personRepository;
    public AddressRepository addressRepository;

    public PersonServiceImplAsync(PersonRepository personRepository, AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Person getPersonById(Long id) throws IllegalArgumentException, NoSuchPersonException, InterruptedException, ExecutionException {
        Assert.notNull(id, "ID cannot be null");
        Future<List<Address>> addresses = addressRepository.getAddressesByIdAsync(1L);
        Person p = personRepository.findByIdAsync(id).get().orElse(null);
        if(null == p){
            addresses.cancel(true);
            throw new NoSuchPersonException("Person not Found");
        }
        p.setAddresses(addresses.get());
        return p;
    }

    @Override
    public List<Person> getAllPeople() throws ExecutionException, InterruptedException {
        List<Person> people = new ArrayList<>();
        personRepository.findAllAsync().get().forEach(person -> {
            person.setAddresses(addressRepository.getAddressesById(1L));
            people.add(person);
        });
        return people;
    }

    @Override
    public Person addPerson(Person p) throws ExecutionException, InterruptedException {
        Assert.notNull(p, "Person cannot be null");
        Future<List<Address>> addresses = addressRepository.getAddressesByIdAsync(1L);
        Person savedPerson = personRepository.saveAsync(p).get();
        savedPerson.setAddresses(addresses.get());
        return savedPerson;
    }

    @Override
    public Person updatePerson(Person p) throws IllegalArgumentException, NoSuchPersonException, ExecutionException, InterruptedException {
        Assert.notNull(p, "Person cannot be null");
        Future<List<Address>> addresses = addressRepository.getAddressesByIdAsync(1L);
        if(!personRepository.existsById(p.getId()))
            throw new NoSuchPersonException("Person not Found");
        Person updatedPerson = personRepository.saveAsync(p).get();
        updatedPerson.setAddresses(addresses.get());
        return updatedPerson;
    }

    @Override
    public Long deletePerson(Person p) throws  IllegalArgumentException, NoSuchPersonException{
        Assert.notNull(p, "Person cannot be null");
        if(!personRepository.existsById(p.getId()))
            throw new NoSuchPersonException("Person not Found");
        personRepository.deleteAsync(p);
        return p.getId();
    }
}
