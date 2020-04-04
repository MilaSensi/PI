package com.pi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="full_name")
    private String fullName;
    private String inn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    private String login;
    @Column(name="password")
    private String passwordHash;
    private String position;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type")
    private PersonType personType;

    public Person(Integer id, String fullName, String inn, Date birthday, String login, String password, String position, PersonType personType) {
        this.id = id;
        this.fullName = fullName;
        this.inn = inn;
        this.birthday = birthday;
        this.login = login;
        this.passwordHash = password;
        this.position = position;
        this.personType = personType;
    }

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }
}
