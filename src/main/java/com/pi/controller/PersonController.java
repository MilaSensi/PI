package com.pi.controller;

import com.pi.model.Person;
import com.pi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Контроллер пользователей
 */
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * получить всех специалистов
     * @return коллеццию специалстов
     */
    @GetMapping("/persons/specialists")
    public Collection<Person> getAllSpecialist() {
        return personService.getAllSpecialist();
    }

    @GetMapping("/persons/clients")
    public Collection<Person> getAllClients() {
        return personService.getAllClients();
    }

    @GetMapping("/persons/photoworkers")
    public Collection<Person> getAllPhotoWorkers() {
        return personService.getAllPhotoWorkers();
    }
}
