package com.perficient.simplecrudproject.runners;

import com.perficient.simplecrudproject.model.Address;
import com.perficient.simplecrudproject.model.Person;
import com.perficient.simplecrudproject.repositories.AddressRepository;
import com.perficient.simplecrudproject.repositories.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeedRunner implements CommandLineRunner {

    public PersonRepository personRepository;

    public SeedRunner(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Kishan","Huliyar"));
        people.add(new Person("Kishan1","Huliyar2"));
        people.add(new Person("Kishan2","Huliyar3"));
        people.add(new Person("Kishan","Huliyar"));
        personRepository.saveAll(people);
    }
}
