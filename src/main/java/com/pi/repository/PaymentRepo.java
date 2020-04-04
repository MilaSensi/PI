package com.pi.repository;

import com.pi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

    @Query("select e from Payment e " +
            " left join fetch e.photoService ps" +
            " left join fetch e.paymentStatus s" +
            " left join fetch e.person p"+
            " left join fetch e.specialist spec"+
            " where e.person.id=:person order by e.dateStart")
    Collection<Payment> findByPerson(@Param("person") Integer person);

    @Query("select e from Payment e order by e.dateStart")
    Collection<Payment> findAllPayments();

    @Query("select e from Payment e where e.specialist.id=:specialist order by e.dateStart")
    Collection<Payment> findAllPaymentsBySpecialist(@Param("specialist") Integer specialist);
}
