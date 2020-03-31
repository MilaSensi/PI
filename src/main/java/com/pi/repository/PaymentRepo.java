package com.pi.repository;

import com.pi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

    @Query("select e from Payment e where e.person.id=:person")
    Collection<Payment> findByPerson(@Param("person") Integer person);

    @Query("select e from Payment e order by e.dateAdd")
    Collection<Payment> findAllPayments();
}
