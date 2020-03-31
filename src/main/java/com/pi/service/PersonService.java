package com.pi.service;

import com.pi.model.Person;
import com.pi.repository.PersonRepo;
import com.pi.repository.PersonTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;

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
     * @param login Строка логина
     * @return Person - объект пользователя
     */
    public Person getByLogin(String login){
        return personRepo.findByLogin(login);
    }

    public String registration(String fullName, String inn, String birthday, String login, String password){
        Person existPerson = personRepo.findByLogin(login);
        if(existPerson!=null){
            return "Пользователь с логином "+login+" уже существует";
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

    public Collection<Person> getAllSpecialist(){
        return personRepo.findAllSpecialist();
    }
}
