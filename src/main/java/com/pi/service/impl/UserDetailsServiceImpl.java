package com.pi.service.impl;

import com.pi.model.Person;
import com.pi.model.SecurityPerson;
import com.pi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.getByLogin(username);
        if (person == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return new SecurityPerson(
                person.getId(),
                person.getFullName(),
                person.getInn(),
                person.getBirthday(),
                person.getLogin(),
                person.getPasswordHash(),
                person.getPosition(),
                person.getPersonType());
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
}
