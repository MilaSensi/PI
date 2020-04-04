package com.pi.service;

import com.pi.model.Payment;
import com.pi.model.Person;
import com.pi.model.dto.DTOPayment;
import com.pi.repository.PaymentRepo;
import com.pi.repository.PaymentStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Service
public class PaymentService {

    private final PaymentRepo paymentRepo;
    private final PaymentStatusRepo paymentStatusRepo;
    private final PersonService personService;
    private final PhotoServiceService photoServiceService;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    public PaymentService(PaymentRepo paymentRepo, PaymentStatusRepo paymentStatusRepo, PersonService personService, PhotoServiceService photoServiceService) {
        this.paymentRepo = paymentRepo;
        this.paymentStatusRepo = paymentStatusRepo;
        this.personService = personService;
        this.photoServiceService = photoServiceService;
    }

    public Collection<Payment> getAllPayments() {
        return paymentRepo.findAllPayments();
    }

    public Collection<Payment> getPaymentsCurrentSpecialist() throws Exception {
        Person person = personService.getCurrentPerson();
        if (person.getPersonType().getCode().equals("SPECIALIST")) {
            throw new Exception("Текущий пользователь не является специалистом");
        }
        return paymentRepo.findAllPaymentsBySpecialist(person.getId());
    }

    public Collection<DTOPayment> reserve(Integer service, Integer spec, String date) throws ParseException {
        Payment payment = new Payment();
        payment.setDateStart(sdf.parse(date));
        payment.setPaymentStatus(paymentStatusRepo.findByCode("RESERVED"));
        payment.setSpecialist(personService.getById(spec));
        payment.setPhotoService(photoServiceService.getById(service));
        payment.setPerson(personService.getCurrentPerson());
        paymentRepo.saveAndFlush(payment);
        return getPayments();
    }

    public Collection<DTOPayment> getPayments() {
        Collection<Payment> payments = paymentRepo.findByPerson(personService.getCurrentPerson().getId());
        Collection<DTOPayment> dtoPayments = new ArrayList<>();
        for (Payment p : payments) {
            dtoPayments.add(new DTOPayment(p));
        }
        return dtoPayments;
    }
}
