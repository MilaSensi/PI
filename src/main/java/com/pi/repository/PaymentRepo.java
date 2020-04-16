package com.pi.repository;

import com.pi.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * репозиторий заказов
 */
@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer> {

    /**
     * Найти заказы пользователя
     * @param person пользователь
     * @return коллекцию заказов
     */
    @Query("select e from Payment e " +
            " left join fetch e.photoService ps" +
            " left join fetch e.paymentStatus s" +
            " left join fetch e.person p" +
            " left join fetch e.specialist spec" +
            " where e.person.id=:person order by e.dateStart, e.id")
    Collection<Payment> findByPerson(@Param("person") Integer person);

    /**
     * Найти все заказы
     * @return коллецию заказов
     */
    @Query("select e from Payment e order by e.dateStart, e.id")
    Collection<Payment> findAllPayments();

    /**
     * Найти заказы специалиста
     * @param specialist специалист
     * @return коллецию заказов
     */
    @Query("select e from Payment e where e.specialist.id=:specialist order by e.dateStart, e.id")
    Collection<Payment> findAllPaymentsBySpecialist(@Param("specialist") Integer specialist);
}
