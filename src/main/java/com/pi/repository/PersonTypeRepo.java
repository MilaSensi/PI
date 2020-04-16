package com.pi.repository;

import com.pi.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * репозиторий типов пользователя
 */
@Repository
public interface PersonTypeRepo extends JpaRepository<PersonType, Integer> {

    /**
     * найти тип пользователя по коду
     * @param code код
     * @return тип пользователя
     */
    @Query("select e from PersonType e where e.code=:code")
    PersonType findByCode(@Param("code") String code);
}
