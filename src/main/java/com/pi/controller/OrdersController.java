package com.pi.controller;

import com.pi.model.dto.DTOPayment;
import com.pi.service.PaymentService;
import com.pi.service.PersonService;
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
        if (personService.getCurrentPerson().getPersonType().getCode().equals("ADMIN")) {
            model.addAttribute("orders", paymentService.getAllPayments());
        }
        if (personService.getCurrentPerson().getPersonType().getCode().equals("SPECIALIST")) {
            model.addAttribute("orders", paymentService.getPaymentsCurrentSpecialist());
        }
        model.addAttribute("personName", personService.getCurrentPerson().getFullName());
        model.addAttribute("paymentStatuses", paymentService.getPaymentStatuses());
        return "orders";
    }

    /**
     * Изменить статус заказа
     */
    @PostMapping(value = "/orders/status")
    @ResponseBody
    public String changeStatus(@RequestParam("paymentId") Integer paymentId, @RequestParam("statusId") Integer statusId) {
        paymentService.changeStatus(paymentId, statusId);
        return "success";
    }
}
