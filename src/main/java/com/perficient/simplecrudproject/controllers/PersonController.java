package com.perficient.simplecrudproject.controllers;

import com.perficient.simplecrudproject.exceptions.NoSuchPersonException;
import com.perficient.simplecrudproject.model.Person;
import com.perficient.simplecrudproject.services.AddressService;
import com.perficient.simplecrudproject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PersonController {

    public PersonService personService;
    public AddressService addressService;

    @Autowired
    public PersonController(PersonService personService, AddressService addressService) {
        this.personService = personService;
        this.addressService = addressService;
    }

    @GetMapping({"/"})
    public ResponseEntity<List<Person>> getAllPeople() {
        return ResponseEntity.ok(personService.getAllPeople());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Person> getPersonByID(@PathVariable String id) {
        Assert.notNull(id, "ID should not be null");
        return ResponseEntity.ok(personService.getPersonById(Long.parseLong(id)));
    }

    @PostMapping({"/"})
    public ResponseEntity<Person> addPerson(@RequestBody Person p) {
        Assert.notNull(p, "Person cannot be null");
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(p));
    }

    @PutMapping({"/"})
    public ResponseEntity<Person> updatePerson(@RequestBody Person p) {
        Assert.notNull(p, "Person cannot be null");
        Assert.notNull(p.getId(), "ID cannot be null");
        return ResponseEntity.ok(personService.updatePerson(p));
    }

    @DeleteMapping({"/"})
    public ResponseEntity<Long> deletePerson(@RequestBody Person p) {
        Assert.notNull(p, "Person cannot be null");
        Assert.notNull(p.getId(), "ID cannot be null");
        return ResponseEntity.ok(personService.deletePerson(p));
    }


}
