package com.pi.repository;

import com.pi.model.PhotoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PhotoServiceRepo extends JpaRepository<PhotoService, Integer> {
}
