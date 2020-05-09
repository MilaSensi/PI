package com.pi.repository;

import com.pi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * репозиторий пользвателей
 */
@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

    /**
     * Найти специалистов
     *
     * @return коллекцию пользователей
     */
    @Query("select e from Person e where e.personType.code='SPECIALIST'")
    Collection<Person> findAllSpecialist();

    /**
     * Найти пользователя по логину
     *
     * @param login логин
     * @return пользователя
     */
    @Query("select e from Person e where e.login=:login")
    Person findByLogin(@Param("login") String login);

    @Query("select e from Person e where e.personType.code='CLIENT'")
    Collection<Person> findAllClients();

    @Query("select e from Person e where e.personType.code='ADMIN' or e.personType.code='SPECIALIST'")
    Collection<Person> findAllPhotoWorkers();
}
