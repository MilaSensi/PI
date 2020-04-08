package com.pi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Заказ
 */
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Дата фотосессии
     */
    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    /**
     * Услуга
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_service")
    private PhotoService photoService;
    /**
     * Описание
     */
    private String description;
    /**
     * Клиент
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person")
    private Person person;
    /**
     * Специалист
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "specialist")
    private Person specialist;
    /**
     * Статус заказа
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status")
    private PaymentStatus paymentStatus;

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

    public PhotoService getPhotoService() {
        return photoService;
    }

    public void setPhotoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getSpecialist() {
        return specialist;
    }

    public void setSpecialist(Person specialist) {
        this.specialist = specialist;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}




