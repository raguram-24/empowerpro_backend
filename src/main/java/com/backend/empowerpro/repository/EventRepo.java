package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event,Long> {
}
