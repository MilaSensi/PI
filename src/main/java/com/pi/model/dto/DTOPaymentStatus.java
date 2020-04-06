package com.pi.model.dto;

import com.pi.model.PaymentStatus;

public class DTOPaymentStatus {

    private Integer id;
    private String name;

    public DTOPaymentStatus(PaymentStatus paymentStatus) {
        this.id = paymentStatus.getId();
        this.name = paymentStatus.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
