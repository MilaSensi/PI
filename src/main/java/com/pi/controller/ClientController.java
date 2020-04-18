package com.pi.controller;

import com.pi.model.dto.DTOPayment;
import com.pi.service.PaymentService;
import com.pi.service.PersonService;
import com.pi.service.PhotoServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Collection;

/**
 * Контроллер клиента
 */
@Controller
public class ClientController {

    private final PersonService personService;
    private final PhotoServiceService photoServiceService;
    private final PaymentService paymentService;

    @Autowired
    public ClientController(PersonService personService, PhotoServiceService photoServiceService, PaymentService paymentService) {
        this.personService = personService;
        this.photoServiceService = photoServiceService;
        this.paymentService = paymentService;
    }

    /**
     * Получить страницу клиента
     */
    @GetMapping(value = "/client")
    public String client(Model model) {
        model.addAttribute("photoServices", photoServiceService.getAllPhotoService());
        model.addAttribute("specialists", personService.getAllSpecialist());
        model.addAttribute("orders", paymentService.getPayments());
        model.addAttribute("personName", personService.getCurrentPerson().getFullName());
        return "client";
    }

    /**
     * Зарезервировать съемку
     */
    @PostMapping(value = "/client/reserve")
    @ResponseBody
    public Collection<DTOPayment> reserve(@RequestParam("service") Integer service,
                                          @RequestParam("spec") Integer spec,
                                          @RequestParam("date") String date) throws ParseException {
        return paymentService.reserve(service, spec, date);
    }
}
