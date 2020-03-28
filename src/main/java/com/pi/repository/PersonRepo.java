package com.pi.repository;

import com.pi.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepo extends org.springframework.data.repository.Repository<Person, Integer> {

    @Query("select e from Person e where e.personType.code='SPECIALIST'")
    Collection<Person> findAllSpecialist();
}
