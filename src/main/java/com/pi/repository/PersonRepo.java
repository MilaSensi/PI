package com.pi.repository;

import com.pi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

    @Query("select e from Person e where e.personType.code='SPECIALIST'")
    Collection<Person> findAllSpecialist();

    @Query("select e from Person e where e.login=:login")
    Person findByLogin(@Param("login") String login);
}
