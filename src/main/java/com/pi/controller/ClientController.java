package com.pi.controller;

import com.pi.service.PersonService;
import com.pi.service.PhotoServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер клиента
 */
@Controller
public class ClientController {

    private final PersonService personService;
    private final PhotoServiceService photoServiceService;

    @Autowired
    public ClientController(PersonService personService, PhotoServiceService photoServiceService) {
        this.personService = personService;
        this.photoServiceService = photoServiceService;
    }

    /**
     * Получить страницу клиента
     */
    @GetMapping(value = "/client")
    public String client(Model model){
        model.addAttribute("photoServices", photoServiceService.getAllPhotoService());
        model.addAttribute("specialists", personService.getAllSpecialist());
        return "client";
    }
}
