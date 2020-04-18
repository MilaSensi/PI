package com.pi.repository;

import com.pi.model.PhotoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * репозиторий услуг
 */
@Repository
public interface PhotoServiceRepo extends JpaRepository<PhotoService, Integer> {

    @Query("select e from PhotoService e where e.name=:name")
    PhotoService findByName(@Param("name") String name);
}
