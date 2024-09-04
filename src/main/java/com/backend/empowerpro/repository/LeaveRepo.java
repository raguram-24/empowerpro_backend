package com.backend.empowerpro.repository;

import com.backend.empowerpro.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepo extends JpaRepository<Leave, Long> {
}
