package com.pi.repository;

import com.pi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {

    @Query("select e from Message e " +
            " left join fetch e.client client" +
            " left join fetch e.admin admin" +
            " where client.id=:client and admin.id=:admin order by e.dateSend desc")
    Collection<Message> findByClientAndAdmin(@Param("client") Integer client, @Param("admin") Integer admin);
}
