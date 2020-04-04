package com.pi.model.dto;

import com.pi.model.Payment;

import java.util.Date;

public class DTOPayment {

    private Integer id;
    private Date dateStart;
    private Date dateEnd;
    private String description;
    private DTOPhotoService photoService;
    private String specialist;
    private String status;

    public DTOPayment(Payment payment) {
        this.id = payment.getId();
        this.dateStart = payment.getDateStart();
        this.dateEnd = payment.getDateEnd();
        this.description = payment.getDescription();
        this.photoService = new DTOPhotoService(payment.getPhotoService());
        this.specialist = payment.getSpecialist().getFullName();
        this.status = payment.getPaymentStatus().getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DTOPhotoService getPhotoService() {
        return photoService;
    }

    public void setPhotoService(DTOPhotoService photoService) {
        this.photoService = photoService;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
