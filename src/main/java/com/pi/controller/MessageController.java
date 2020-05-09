package com.pi.controller;

import com.pi.model.Message;
import com.pi.model.Person;
import com.pi.service.MessageService;
import com.pi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class MessageController {

    private final MessageService messageService;
    private final PersonService personService;

    @Autowired
    public MessageController(MessageService messageService, PersonService personService) {
        this.messageService = messageService;
        this.personService = personService;
    }

    @GetMapping("/messages")
    public String getMessagesPage(Model model) {
        Person currentPerson = personService.getCurrentPerson();
        if (currentPerson.getPersonType().getCode().equals("CLIENT")) {
            model.addAttribute("personList", personService.getAllPhotoWorkers());
            model.addAttribute("personListType", "ADMINS");
        } else {
            model.addAttribute("personList", personService.getAllClients());
            model.addAttribute("personListType", "CLIENTS");
        }
        model.addAttribute("currentPersonId", currentPerson.getId());
        model.addAttribute("currentPersonType", currentPerson.getPersonType().getCode());
        return "messages";
    }

    @GetMapping("/messages/get")
    @ResponseBody
    public Collection<Message> getAllSpecialist(@RequestParam("client") Integer client, @RequestParam("admin") Integer admin) {
        return messageService.getMessages(client, admin);
    }

    @PostMapping("/messages/send")
    @ResponseBody
    public Collection<String> sendMessage(@RequestParam("message") String message,
                                          @RequestParam("sender") String sender,
                                          @RequestParam("client") Integer client,
                                          @RequestParam("admin") Integer admin) throws Exception {
        messageService.sendMessage(message, sender, client, admin);
        return new ArrayList<>();
    }
}
