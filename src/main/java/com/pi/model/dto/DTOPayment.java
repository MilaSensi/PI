package com.pi.model.dto;

import com.pi.model.Payment;

import java.text.SimpleDateFormat;

public class DTOPayment {

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    private Integer id;
    private String dateStart;
    private String description;
    private DTOPhotoService photoService;
    private String specialist;
    private String status;
    private Integer statusId;

    public DTOPayment(Payment payment) {
        this.id = payment.getId();
        this.dateStart = sdf.format(payment.getDateStart());
        this.description = payment.getDescription();
        this.photoService = new DTOPhotoService(payment.getPhotoService());
        this.specialist = payment.getSpecialist().getFullName();
        this.status = payment.getPaymentStatus().getName();
        this.statusId = payment.getPaymentStatus().getId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
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

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }
}
