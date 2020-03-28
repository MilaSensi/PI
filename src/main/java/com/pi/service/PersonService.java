package com.pi.service;

import com.pi.model.Person;
import com.pi.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

    private final PersonRepo personRepo;

    @Autowired
    public PersonService(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    public Collection<Person> getAllSpecialist(){
        return personRepo.findAllSpecialist();
    }
}
