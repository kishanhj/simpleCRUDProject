package com.perficient.simplecrudproject.controllers;

import com.perficient.simplecrudproject.model.Person;
import com.perficient.simplecrudproject.services.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
@Slf4j
@RequestMapping({"/person"})
public class PersonController {

    public PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping({"/"})
    public ResponseEntity<List<Person>> getAllPeople() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(personService.getAllPeople());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Person> getPersonByID(@PathVariable String id) throws ExecutionException, InterruptedException {
        Assert.notNull(id, "ID should not be null");
        return ResponseEntity.ok(personService.getPersonById(Long.parseLong(id)));
    }

    @PostMapping({"/"})
    public ResponseEntity<Person> addPerson(@RequestBody Person p) throws ExecutionException, InterruptedException {
        Assert.notNull(p, "Person cannot be null");
        log.info("Recieved request for : "+ p);
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(p));
    }

    @PutMapping({"/"})
    public ResponseEntity<Person> updatePerson(@RequestBody Person p) throws ExecutionException, InterruptedException {
        Assert.notNull(p, "Person cannot be null");
        Assert.notNull(p.getId(), "ID cannot be null");
        return ResponseEntity.ok(personService.updatePerson(p));
    }

    @DeleteMapping({"/"})
    public ResponseEntity<Long> deletePerson(@RequestBody Person p) throws ExecutionException, InterruptedException {
        Assert.notNull(p, "Person cannot be null");
        Assert.notNull(p.getId(), "ID cannot be null");
        return ResponseEntity.ok(personService.deletePerson(p));
    }


}
