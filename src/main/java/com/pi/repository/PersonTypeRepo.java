package com.pi.repository;

import com.pi.model.Person;
import com.pi.model.PersonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonTypeRepo extends JpaRepository<PersonType, Integer> {

    @Query("select e from PersonType e where e.code=:code")
    PersonType findByCode(@Param("code") String code);
}
