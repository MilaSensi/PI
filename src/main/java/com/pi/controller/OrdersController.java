package com.pi.controller;

import com.pi.service.PaymentService;
import com.pi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер заказов
 */
@Controller
public class OrdersController {

    private final PersonService personService;
    private final PaymentService paymentService;

    @Autowired
    public OrdersController(PersonService personService, PaymentService paymentService) {
        this.personService = personService;
        this.paymentService = paymentService;
    }

    /**
     * Получить страницу c заказами
     */
    @GetMapping(value = "/orders")
    public String client(Model model) throws Exception {
        if(personService.getCurrentPerson().getPersonType().getCode().equals("ADMIN")){
            model.addAttribute("orders", paymentService.getAllPayments());
        }
        if(personService.getCurrentPerson().getPersonType().getCode().equals("SPECIALIST")){
            model.addAttribute("orders", paymentService.getPaymentsCurrentSpecialist());
        }
        return "orders";
    }
}
