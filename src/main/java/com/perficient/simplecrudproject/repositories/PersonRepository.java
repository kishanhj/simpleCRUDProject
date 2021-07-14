package com.perficient.simplecrudproject.repositories;

import com.perficient.simplecrudproject.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.Future;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    @Async
    default Future<Optional<Person>> findByIdAsync(Long id){
        return new AsyncResult<Optional<Person>>(findById(id));
    }

    @Async
    default Future<Iterable<Person>> findAllAsync(){
        return new AsyncResult<>(findAll());
    }

    @Async
    default Future<Person> saveAsync(Person p){
        return new AsyncResult<>(save(p));
    }

    @Async
    default void deleteAsync(Person p){
        delete(p);
    }

}
