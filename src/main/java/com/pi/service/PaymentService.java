package com.pi.service;

import com.pi.model.Payment;
import com.pi.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PaymentService {

    private final PaymentRepo paymentRepo;

    @Autowired
    public PaymentService(PaymentRepo paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    public Collection<Payment> getAllPayments(){
      return paymentRepo.findAllPayments();
  }
}
