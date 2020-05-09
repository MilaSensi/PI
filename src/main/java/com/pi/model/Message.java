package com.pi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "message")
public class Message implements Serializable {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Сообщение
     */
    private String message;
    /**
     * Отправитель
     */
    private String sender;
    /**
     * Дата отправки
     */
    @Column(name = "date_send")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSend;
    /**
     * Клиент
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client")
    private Person client;
    /**
     * Админ
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin")
    private Person admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public Person getClient() {
        return client;
    }

    public void setClient(Person client) {
        this.client = client;
    }

    public Person getAdmin() {
        return admin;
    }

    public void setAdmin(Person admin) {
        this.admin = admin;
    }
}
