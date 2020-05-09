package com.pi.service;

import com.pi.model.Person;
import com.pi.model.SecurityPerson;
import com.pi.repository.PersonRepo;
import com.pi.repository.PersonTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Optional;

@Service
public class PersonService {

    private final PasswordEncoder passwordEncoder;
    private final PersonRepo personRepo;
    private final PersonTypeRepo personTypeRepo;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    @Autowired
    public PersonService(PasswordEncoder passwordEncoder, PersonRepo personRepo, PersonTypeRepo personTypeRepo) {
        this.passwordEncoder = passwordEncoder;
        this.personRepo = personRepo;
        this.personTypeRepo = personTypeRepo;
    }

    /**
     * Получить пользователя
     *
     * @param login Строка логина
     * @return Person - объект пользователя
     */
    public Person getByLogin(String login) {
        return personRepo.findByLogin(login);
    }

    /**
     * Загеристрировать пользователя
     *
     * @param fullName фио
     * @param inn      инн
     * @param birthday дата рождения
     * @param login    логин
     * @param password пароль
     * @return текст ошибки если не удалось зарегистрировать или null при успехе
     */
    public String registration(String fullName, String inn, String birthday, String login, String password) {
        Person existPerson = personRepo.findByLogin(login);
        if (existPerson != null) {
            return "Пользователь с логином " + login + " уже существует";
        }
        Person person = new Person();
        person.setFullName(fullName);
        person.setInn(inn);
        try {
            person.setBirthday(sdf.parse(birthday));
        } catch (ParseException e) {
            return "Не удалось прочитать дату рождения";
        }
        person.setLogin(login);
        person.setPasswordHash(passwordEncoder.encode(password));
        person.setPersonType(personTypeRepo.findByCode("CLIENT"));
        personRepo.save(person);
        return null;
    }

    /**
     * Получить всех специалистов
     *
     * @return коллекцию специалистов
     */
    public Collection<Person> getAllSpecialist() {
        return personRepo.findAllSpecialist();
    }

    /**
     * Получить текушего пользователя
     *
     * @return пользователя
     */
    public Person getCurrentPerson() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityPerson securityPerson = (SecurityPerson) auth.getPrincipal();
        Optional<Person> optionalPerson = personRepo.findById(securityPerson.getId());
        return optionalPerson.orElse(null);
    }

    /**
     * получить пользователя по id
     *
     * @param id id пользователя
     * @return пользователя
     */
    public Person getById(Integer id) {
        return personRepo.getOne(id);
    }

    public Collection<Person> getAllClients() {
        return personRepo.findAllClients();
    }

    public Collection<Person> getAllPhotoWorkers() {
        return personRepo.findAllPhotoWorkers();
    }
}
