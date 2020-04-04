package com.pi.repository;

import com.pi.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentStatusRepo extends JpaRepository<PaymentStatus, Integer> {

    @Query("select e from PaymentStatus e where e.code=:code")
    PaymentStatus findByCode(@Param("code") String code);
}
