package com.pi.controller;

import com.pi.model.Person;
import com.pi.repository.PersonRepo;
import com.pi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/persons/specialists")
    public Collection<Person> getAllSpecialist() {
        return personService.getAllSpecialist();
    }
}
